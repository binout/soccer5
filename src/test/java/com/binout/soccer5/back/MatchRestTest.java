package com.binout.soccer5.back;

import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import junit.framework.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import javax.transaction.UserTransaction;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Date;

@RunWith(Arquillian.class)
public class MatchRestTest {

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MatchEJB.class.getPackage())
                .addPackage(Match.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void can_list_match() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8080).path("test/rest/match").build();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine().toString());
    }

    @Test
    public void can_list_next_match() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8080).path("test/rest/match/next").build();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine().toString());
    }
}
