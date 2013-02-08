package com.binout.soccer5.back;

import com.binout.soccer5.entity.Player;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.util.List;

@RunWith(Arquillian.class)
public class PlayerEJBTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(PlayerEJB.class.getPackage())
                .addPackage(Player.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @EJB
    PlayerEJB  playerEJB;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        startTransaction();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from Player").executeUpdate();
        utx.commit();
    }


    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    @Test
    public void listPlayers_should_return_empty_list() {
        List<Player> list = playerEJB.listPlayers();

        Assert.assertNotNull(list);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void register_player_should_add_player() {
        Player p = new Player();
        p.setName("benoit");
        p.setMail("a mail");

        playerEJB.registerPlayer(p);

        TypedQuery<Player> query = em.createQuery("select p from Player p", Player.class);
        List<Player> result = query.getResultList();

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }
}
