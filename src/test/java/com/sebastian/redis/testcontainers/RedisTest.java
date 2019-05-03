package com.sebastian.redis.testcontainers;

import java.util.logging.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

/**
 * test utilizando redis con testcontainers.
 *
 * @author Sebastian Avila A,
 *
 */
public class RedisTest {

    private static final Logger LOGGER = Logger.getLogger(RedisTest.class.getName());

    @Rule
    public GenericContainer redis = RedisContainerSetUp.puertoDinamico();

    @Test
    public void redisPuertoDinamicoDisponible() {
        final var puerto = redis.getFirstMappedPort();
        Assertions.assertThat(puerto).isGreaterThan(0);
    }

    @Test
    public void clientePuedeObtenerConexion() {
        var cliente = new Jedis(redis.getContainerIpAddress(), redis.getFirstMappedPort());
        cliente.set("hola", "chao");
        Assertions.assertThat(cliente.get("hola")).isEqualTo("chao");
    }

}
