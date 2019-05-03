package com.sebastian.redis.testcontainers;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import java.util.function.Consumer;
import org.testcontainers.containers.GenericContainer;

/**
 * inicializacion de redis para las pruebas.
 *
 * @author Sebastián Ávila A.
 */
public final class RedisContainerSetUp {

    /**
     * inicia redis en un puerto especifico.
     *
     * @return
     */
    public static GenericContainer puertoEspecifico() {
        final int containerExposedPort = 6379;
        final Consumer<CreateContainerCmd> config = e -> 
                e.withPortBindings(new PortBinding(Ports.Binding.bindPort(10000),
                new ExposedPort(containerExposedPort)));
        
        return new GenericContainer("redis:5.0.3-alpine")
                .withExposedPorts(containerExposedPort)
                .withCreateContainerCmdModifier(config);
    }

    /**
     * inicia redis en un puerto dinamico.
     *
     * @return
     */
    public static GenericContainer puertoDinamico() {
        return new GenericContainer<>("redis:5.0.3-alpine")
                .withExposedPorts(6379);
    }
}
