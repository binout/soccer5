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
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@RunWith(Arquillian.class)
public class PlayerRestTest {

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(PlayerEJB.class.getPackage())
                .addPackage(Player.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void can_list_player() throws Exception {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8080).path("test/rest/player").build();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri.toString());

        HttpResponse response = httpclient.execute(httpGet);

        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine().toString());
    }

}
