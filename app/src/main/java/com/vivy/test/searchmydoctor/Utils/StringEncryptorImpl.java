package com.vivy.test.searchmydoctor.Utils;

public class StringEncryptorImpl implements StringEncryptor {
    private static final String key = "2BON2B"; // The key for 'encrypting' and 'decrypting'.

    public String encryptString(String str) {
        StringBuilder stringBuffer = new StringBuilder(str);

        int stringLength = str.length();
        int keyLength = key.length();

       //  For each character in our string, encrypt it...
        for (int i = 0, j = 0; i < stringLength; i++, j++)
        {
            if (j >= keyLength) j = 0;  // Wrap 'round to beginning of key string.

            // XOR the chars together. Must cast back to char to avoid compile error.
            stringBuffer.setCharAt(i, (char) (str.charAt(i) ^ key.charAt(j)));
        }

         return stringBuffer.toString();

    }
}