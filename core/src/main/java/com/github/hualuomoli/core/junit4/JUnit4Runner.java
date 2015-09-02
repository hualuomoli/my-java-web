package com.github.hualuomoli.core.junit4;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/*.xml" })
public class JUnit4Runner {

	protected static final Logger logger = LoggerFactory.getLogger(JUnit4Runner.class);

}
