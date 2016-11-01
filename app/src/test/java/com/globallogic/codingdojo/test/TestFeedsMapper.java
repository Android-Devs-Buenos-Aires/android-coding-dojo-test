package com.globallogic.codingdojo.test;

import com.globallogic.codingdojo.data.dto.RssDTO;
import com.globallogic.codingdojo.di.component.DaggerTestPresenterComponent;
import com.globallogic.codingdojo.di.module.TestPresenterModule;
import com.globallogic.codingdojo.domain.mappers.Transformable;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.mock.RSSMockFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author facundo.mengoni
 * @since 0.1
 */
@RunWith(JUnit4.class)
public class TestFeedsMapper {

    @Inject
    RSSMockFactory mockFactory;
    @Inject
    Transformable<RssDTO, RSS> mapper;

    @Before
    public void config() {
        DaggerTestPresenterComponent.builder().testPresenterModule(new TestPresenterModule()).build().inject(this);
    }

    @Test
    public void testTransformRss() {
        RssDTO rssWithTwoItems = mockFactory.getRssWithTwoItems();
        RSS rss = mapper.transform(rssWithTwoItems);
        assertNotNull(rss);
        assertNotNull(rss.getItems());
        assertEquals(2, rss.getItems().size());
    }

    @Test
    public void testNoDataTransformRss() {
        RssDTO rssWithTwoItems = mockFactory.getRssWithNoData();
        RSS rss = mapper.transform(rssWithTwoItems);
        assertNotNull(rss);
        assertNotNull(rss.getItems());
        assertEquals(0, rss.getItems().size());
    }
}