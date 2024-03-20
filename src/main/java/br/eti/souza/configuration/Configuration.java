package br.eti.souza.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configurações da aplicação.
 * @author Alan Moraes Souza
 */
public class Configuration {

    /** Logger para esta classe. */
    private final static Logger LOGGER = Logger.getLogger(Configuration.class.getName());
    /** Instâncias de configurações. */
    private final static Map<String, Configuration> CONFIGURATIONS = new HashMap<>();
    /** Properties da aplicação. */
    private final Properties properties = new Properties();

    /**
     * Construtor que carrega as configurações de um arquivo de propriedades.
     * @param path Path do arquivo de configurações.
     */
    private Configuration(String name) {
        try {
            if (name != null && !name.isBlank()) {
                this.properties.load(this.getClass().getClassLoader().getResourceAsStream(name));
            }
        } catch (UnsupportedOperationException | SecurityException | IllegalArgumentException | NullPointerException | IOException e) {
            Configuration.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou nulo se não achar.
     */
    public String get(String key, String defaultValue) {
        var value = this.get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou nulo se não achar.
     */
    public Integer getAsInteger(String key, Integer defaultValue) {
        var value = this.getAsInteger(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Retorna valor da configuração, ou nulo se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @return Valor da configuração ou nulo se não achar.
     */
    public String get(String key) {
        try {
            if (key != null && !key.isBlank()) {
                key = key.trim();
                var value = System.getenv(key);
                if (value == null || value.isBlank()) {
                    value = System.getProperty(key);
                    if (value == null || value.isBlank()) {
                        value = this.properties.getProperty(key);
                    }
                }
                if (value != null && !value.isBlank()) {
                    return value.trim();
                }
            }
        } catch (SecurityException | IllegalArgumentException | NullPointerException e) {
            Configuration.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retorna valor como numero inteiro da configuração, ou nulo se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @return Valor da configuração ou nulo se não achar.
     */
    public Integer getAsInteger(String key) {
        var value = this.get(key);
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            return null;
        }
    }

    /**
     * Retorna a configuração padrão que tem como properties interno souza.properties.
     * Equivalente a Configuration.getInstance("souza").
     * @return Configuração padrão.
     */
    public static Configuration getInstance() {
        return Configuration.getInstance("souza");
    }

    /**
     * Retorna a configuração de acordo com o nome, usando como properties interno [name].properties.
     * @param name Nome da configuração.
     * @return Configuração de acordo com o nome.
     */
    public static Configuration getInstance(String name) {
        if (!Configuration.CONFIGURATIONS.containsKey(name)) {
            Configuration.CONFIGURATIONS.put(name, new Configuration(name + ".properties"));
        }
        return Configuration.CONFIGURATIONS.get(name);
    }
}
