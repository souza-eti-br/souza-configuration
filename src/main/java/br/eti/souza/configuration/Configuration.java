package br.eti.souza.configuration;

import java.io.IOException;
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

    /**
     * Retorna a configuração padrão que tem como properties interno souza.properties.
     * Equivalente a Configuration.getInstance("souza").
     * @return Configuração padrão.
     */
    public static Configuration getInstance() {
        return Configuration.getInstance("souza");
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
        if (key == null || key.isBlank()) {
            return null;
        }
        key = key.trim();
        try {
            var value = System.getenv(key);
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
            value = System.getProperty(key);
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
            value = this.properties.getProperty(key);
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
        } catch (SecurityException e) {
            Configuration.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
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
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public char get(String key, char defaultValue) {
        var value = this.get(key);
        return value != null ? value.charAt(0) : defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public int get(String key, int defaultValue) {
        var value = this.get(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public long get(String key, long defaultValue) {
        var value = this.get(key);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public float get(String key, float defaultValue) {
        var value = this.get(key);
        if (value != null) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public double get(String key, double defaultValue) {
        var value = this.get(key);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Retorna valor da configuração, ou o valor padrão se não achar.
     * Ordem de procura:
     * 1- Variavel de sistema: System.getenv(key).
     * 2- Propriedade de execução: System.getProperty(key).
     * 3- Arquivo de propriedade relacionado a esta configuração: this.properties.getProperty(key).
     * @param key Chave da configuração.
     * @param defaultValue Valor padrão.
     * @return Valor da configuração ou o valor padrão se não achar.
     */
    public boolean get(String key, boolean defaultValue) {
        var value = this.get(key);
        if (value != null) {
            try {
                return Boolean.parseBoolean(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
