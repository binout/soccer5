package com.binout.soccer5.back;

import com.binout.soccer5.entity.Match;
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
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class MatchEJBTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MatchEJB.class.getPackage())
                .addPackage(Match.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @EJB
    PlayerEJB  playerEJB;

    @EJB
    MatchEJB  matchEJB;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        startTransaction();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from Match").executeUpdate();
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
    public void cannot_register_player_to_match_if_already_registed() {
        Player p = new Player();
        p.setName("benoit");
        p.setMail("a mail");

        playerEJB.registerPlayer(p);

        Match m = new Match();
        Date date = new Date();
        m.setDate(date);

        matchEJB.registerMatch(m);

        matchEJB.registerPlayerToMatch(m, p);

        Match match = matchEJB.findMatch(date);
        Assert.assertEquals(1, match.getPlayers().size());

        matchEJB.registerPlayerToMatch(m, p);

        Assert.assertEquals(1, match.getPlayers().size());
    }
}
