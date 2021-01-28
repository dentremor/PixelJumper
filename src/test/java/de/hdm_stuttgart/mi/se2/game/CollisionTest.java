package de.hdm_stuttgart.mi.se2.game;

import de.hdm_stuttgart.mi.se2.game.util.CollisionUtil;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class CollisionTest {

    /**
     * Dummy test method
     */


    @Test
    public void testIntersectIsTrue() {

        Rectangle r1 = new Rectangle(100,100,100,100);
        Rectangle r2 = new Rectangle(100,100,100,100);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));


        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(150,150,100,100);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(50,50,100,100);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(199,199,100,100);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(125,125,50,50);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));


    }

    @Test
    public void testIntersectFalse(){
        Rectangle r1 = new Rectangle(100,100,100,100);
        Rectangle r2 = new Rectangle(250,100,100,100);

        Assert.assertFalse(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(-50,100,100,100);

        Assert.assertFalse(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(100,100,50,50);
        r2 = new Rectangle(100,180,50,50);

        Assert.assertFalse(CollisionUtil.getInstance().intersects(r1,r2));


        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(201,100,100,100);

        Assert.assertFalse(CollisionUtil.getInstance().intersects(r1,r2));
    }

    @Test
    public void testIntersectDifferentSize(){
        Rectangle r1 = new Rectangle(100,100,50,30);
        Rectangle r2 = new Rectangle(130,100,30,30);

        Assert.assertTrue(CollisionUtil.getInstance().intersects(r1,r2));

        r1 = new Rectangle(0,0,33,77);
        r2 = new Rectangle(100,20,50,63);

        Assert.assertFalse(CollisionUtil.getInstance().intersects(r1,r2));
    }

    @Test
    public void testCollisionSide(){
        Rectangle r1 = new Rectangle(100,100,100,100);
        Rectangle r2 = new Rectangle(100,100,100,100);

        Assert.assertEquals(0,CollisionUtil.getInstance().getCollisionSide(r1,r2));

        r1 = new Rectangle(0,0,100,100);
        r2 = new Rectangle(60,50,100,100);

        Assert.assertEquals(2,CollisionUtil.getInstance().getCollisionSide(r1,r2));


        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(0,0,100,100);

        Assert.assertEquals(1,CollisionUtil.getInstance().getCollisionSide(r1,r2));

        r1 = new Rectangle(100,100,100,100);
        r2 = new Rectangle(0,180,100,100);

        Assert.assertEquals(3,CollisionUtil.getInstance().getCollisionSide(r1,r2));

    }


}
