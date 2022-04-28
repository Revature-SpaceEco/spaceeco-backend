package com.revature.spaceecobackend.service;

import dev.samstevens.totp.qr.QrData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Service
public class MfaService {

    @Autowired
    private SecretGenerator secretGenerator;

    @Autowired
    private QrDataFactory qrDataFactory;

    @Autowired
    private QrGenerator qrGenerator;

    @Autowired
    private CodeVerifier verifier;

    public String getSecret() {
        return secretGenerator.generate();

    }

    public String getQrCode(String secret, String email) throws QrGenerationException {

        QrData data = qrDataFactory.newBuilder()
                .label(email)
                .secret(secret)
                .issuer("SpaceEco")
                .build();

        // Generate the QR code image data as a base64 string which
        // can be used in an <img> tag:
        String qrCodeImage = getDataUriForImage(
                qrGenerator.generate(data),
                qrGenerator.getImageMimeType()
                );
        return qrCodeImage;
    }

    public boolean verifyCode(String code, String secret) {

        if (verifier.isValidCode(secret, code)) {
            return true;
        }
        return false;
    }


}
