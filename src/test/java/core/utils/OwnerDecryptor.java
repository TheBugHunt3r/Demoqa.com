package core.utils;

import org.aeonbits.owner.crypto.Decryptor;

public class OwnerDecryptor implements Decryptor {

    @Override
    public String decrypt(String value) {
        return EncryptionUtils.decrypt(value);
    }

    @Override
    public String decrypt(String value, String key) {
        return decrypt(value);
    }
}
