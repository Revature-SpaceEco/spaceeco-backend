package com.revature.spaceecobackend.controller;

import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Controller
public class MfaSetupController {

    @Autowired
    private SecretGenerator secretGenerator;

    @Autowired
    private QrDataFactory qrDataFactory;

    @Autowired
    private QrGenerator qrGenerator;


    @GetMapping("/mfa/setup")
    public String setupDevice() throws QrGenerationException {
        // Generate and store the secret
        String secret = secretGenerator.generate();

        QrData data = qrDataFactory.newBuilder()
                .label("example@example.com")
                .secret(secret)
                .issuer("AppName")
                .period(30)
                .build();

        // Generate the QR code image data as a base64 string which
        // can be used in an <img> tag:
        String qrCodeImage = getDataUriForImage(
                qrGenerator.generate(data),
                qrGenerator.getImageMimeType()
        );
        return qrCodeImage;
    }
}