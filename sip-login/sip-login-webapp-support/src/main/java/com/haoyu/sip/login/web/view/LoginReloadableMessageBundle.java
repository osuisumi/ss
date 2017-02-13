package com.haoyu.sip.login.web.view;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * An extension of the {@link ReloadableResourceBundleMessageSource} whose sole concern
 * is to print a WARN message in cases where a language key is not found in the active and
 * default bundles.
 * 
 * <p>Note: By default, if a key not found in a localized bundle, Spring will auto-fallback
 * to the default bundle that is <code>messages.properties</code>. However, if the key is also
 * not found in the default bundle, and {@link #setUseCodeAsDefaultMessage(boolean)}
 * is set to true, then only the requested code itself will be used as the message to display.
 * In this case, the class will issue a WARN message instructing the caller that the bundle
 * needs further attention. If {@link #setUseCodeAsDefaultMessage(boolean)} is set to false,
 * only then a <code>null</code> value will be returned, which subsequently causes an instance
 * of {@link org.springframework.context.NoSuchMessageException} to be thrown.
 * 
 * @author Misagh Moayyed
 * @since 4.0
 */
public class LoginReloadableMessageBundle extends ReloadableResourceBundleMessageSource {

    private String[] basenames;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected String getDefaultMessage(final String code) {
        final String messageToReturn = super.getDefaultMessage(code);
        if (!StringUtils.isBlank(messageToReturn) && messageToReturn.equals(code)) {
            logger.warn("The code [{}] cannot be found in the default language bundle and will "
                    + "be used as the message itself.", code);
        }
        return messageToReturn;
    }

    @Override
    protected String getMessageInternal(final String code, final Object[] args, final Locale locale) {
        boolean foundCode = false;
        
        if (!locale.equals(Locale.ENGLISH)) {
          for (int i = 0; !foundCode && i < this.basenames.length; i++) {
              final String filename = this.basenames[i] + "_" + locale.getLanguage();
              
              logger.debug("Examining language bundle [{}] for the code [{}]", filename, code);
              final PropertiesHolder holder = this.getProperties(filename);
              foundCode =  holder != null && holder.getProperties() != null
                                     && holder.getProperty(code) != null;  
          }       
          
          if (!foundCode) {
              logger.warn("The code [{}] cannot be found in the language bundle for the locale [{}]",
                      code, locale);
          }
        }
        return super.getMessageInternal(code, args, locale);
    }

    @Override
    public void setBasenames(final String... basenames) {
        this.basenames = basenames;
        super.setBasenames(basenames);
    }
    
    

}
