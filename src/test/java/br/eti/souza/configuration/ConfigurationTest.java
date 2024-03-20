package br.eti.souza.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Configurações da aplicação.
 * @author Alan Moraes Souza
 */
public class ConfigurationTest {

    /** Teste para obter instância padrão. */
    @Test
    public void testGetInstanceDefault() {
        Assertions.assertEquals("souzaProperties", Configuration.getInstance().get("name"));
    }

    /** Teste para obter instância nomeada. */
    @Test
    public void testGetInstanceNamed() {
        Assertions.assertEquals("otherProperties", Configuration.getInstance("other").get("name"));
    }

    /** Teste para obter configuração string de variaveis de ambiente. */
    @Test
    public void testGetFromEnv() {
        var path = Configuration.getInstance().get("PATH");
        Assertions.assertNotEquals("PATH do souza.properties", path);
        Assertions.assertNotNull(path);
    }

    /** Teste para obter configuração string de propriedades do sistema. */
    @Test
    public void testGetFromSystem() {
        var osName = Configuration.getInstance().get("os.name");
        Assertions.assertNotEquals("OS do souza.properties", osName);
        Assertions.assertNotNull(osName);
    }

    /** Teste para obter configuração string. */
    @Test
    public void testGetString() {
        Assertions.assertEquals("texto da propriedade", Configuration.getInstance().get("string.texto"));
        Assertions.assertEquals("texto da propriedade", Configuration.getInstance().get("string.texto", "valor padrão"));
        Assertions.assertEquals("valor padrão", Configuration.getInstance().get("string.inexistente", "valor padrão"));
    }

    /** Teste para obter configuração char. */
    @Test
    public void testGetChar() {
        Assertions.assertEquals('c', Configuration.getInstance().get("char.ok", 'v'));
        Assertions.assertEquals('t', Configuration.getInstance().get("char.string", 'v'));
        Assertions.assertEquals('9', Configuration.getInstance().get("char.numero", 'v'));
        Assertions.assertEquals('v', Configuration.getInstance().get("char.inexistente", 'v'));
    }

    /** Teste para obter configuração int. */
    @Test
    public void testGetInt() {
        Assertions.assertEquals((int) 111, Configuration.getInstance().get("int.ok", (int) 2));
        Assertions.assertEquals((int) 3, Configuration.getInstance().get("int.string", (int) 3));
        Assertions.assertEquals((int) 4, Configuration.getInstance().get("int.inexistente", (int) 4));
    }

    /** Teste para obter configuração long. */
    @Test
    public void testGetLong() {
        Assertions.assertEquals((long) 111, Configuration.getInstance().get("int.ok", (long) 2));
        Assertions.assertEquals((long) 3, Configuration.getInstance().get("int.string", (long) 3));
        Assertions.assertEquals((long) 4, Configuration.getInstance().get("long.inexistente", (long) 4));
    }

    /** Teste para obter configuração float. */
    @Test
    public void testGetFloat() {
        Assertions.assertEquals((float) 111.13, Configuration.getInstance().get("decimal.ok", (float) 2));
        Assertions.assertEquals((float) 3, Configuration.getInstance().get("int.string", (float) 3));
        Assertions.assertEquals((float) 4, Configuration.getInstance().get("long.inexistente", (float) 4));
    }

    /** Teste para obter configuração double. */
    @Test
    public void testGetDouble() {
        Assertions.assertEquals((double) 111.13, Configuration.getInstance().get("decimal.ok", (double) 2));
        Assertions.assertEquals((double) 3, Configuration.getInstance().get("int.string", (double) 3));
        Assertions.assertEquals((double) 4, Configuration.getInstance().get("long.inexistente", (double) 4));
    }

    /** Teste para obter configuração boolean. */
    @Test
    public void testGetBoolean() {
        Assertions.assertEquals(true, Configuration.getInstance().get("boolean.ok", false));
        Assertions.assertEquals(false, Configuration.getInstance().get("boolean.numero", false));
        Assertions.assertEquals(false, Configuration.getInstance().get("boolean.inexistente", false));
    }
}
