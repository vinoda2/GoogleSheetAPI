package eventorganization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExampleJUINT {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@BeforeAll
	void setUp() {
		log.info("this is running in set up method in Junit");
		System.err.println("this is running in set up method in Junit");
	}
}
