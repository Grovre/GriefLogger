package me.grovre.grieflogger;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class UuidCoder {

    public static String encodeToBase64(UUID uuid) throws IOException {
        ByteArrayOutputStream bytearroutstr = new ByteArrayOutputStream();
        ObjectOutputStream objoutstr = new ObjectOutputStream(bytearroutstr);
        objoutstr.writeObject(uuid);
        byte[] ba = bytearroutstr.toByteArray();
        return Base64.getEncoder().encodeToString(ba);
    }

    public static UUID decodeFromBase64(String encoded) throws IOException, ClassNotFoundException {
        byte[] ba = Base64.getDecoder().decode(encoded);
        ByteArrayInputStream bytearrinstr = new ByteArrayInputStream(ba);
        ObjectInputStream objinstr = new ObjectInputStream(bytearrinstr);
        return (UUID) objinstr.readObject();
    }
}
