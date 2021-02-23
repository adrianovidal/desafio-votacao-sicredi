package br.com.votacao.unittest;

import br.com.votacao.controller.AllControllersTests;
import br.com.votacao.service.AllServiceTests;
import br.com.votacao.share.converter.AllConvertersTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllControllersTests.class,
        AllServiceTests.class,
        AllConvertersTests.class
})
public class UnitTests {
}
