package com.example.tfgfontanet.ui.seguridad;

import com.example.tfgfontanet.common.utiles.Constantes;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class MainKeyStore {

    public static void main(String[] args) throws Exception{

        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Constantes.RSA);
        keyPairGenerator.initialize(Constantes.KEYSIZE, new SecureRandom());
        KeyPair keyPairRSA = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPairRSA.getPrivate();
        PublicKey publicKey = keyPairRSA.getPublic();

        X509V3CertificateGenerator certificateGenerator = new X509V3CertificateGenerator();
        certificateGenerator.setSerialNumber(BigInteger.valueOf(Constantes.VAL));
        certificateGenerator.setSubjectDN(new X509Principal(Constantes.CN_SERVIDOR));
        certificateGenerator.setIssuerDN(new X509Principal(Constantes.CN_SERVIDOR));
        certificateGenerator.setPublicKey(publicKey);
        certificateGenerator.setNotBefore(Date.from(LocalDate.now().plusDays(Constantes.DAYS_TO_ADD).atStartOfDay().toInstant(ZoneOffset.UTC)));
        certificateGenerator.setNotAfter(new Date());
        certificateGenerator.setSignatureAlgorithm(Constantes.SHA_256_WITH_RSAENCRYPTION);

        X509Certificate certificate =  certificateGenerator.generate(privateKey);

        String username = Constantes.SERVER;
        char[] password = Constantes.KEYSTORE_PASSWORD.toCharArray();

        KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
        ks.load(null, password);
        ks.setCertificateEntry(username, certificate);
        ks.setKeyEntry(username, privateKey, password, new Certificate[]{certificate});
        FileOutputStream fos = new FileOutputStream(Constantes.KEYSTORE_PFX);
        ks.store(fos, password);
        fos.close();
    }
}
