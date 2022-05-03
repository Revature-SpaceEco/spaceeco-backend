package com.revature.spaceecobackend.service;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MfaServiceTest {
    @Mock
    private SecretGenerator secretGenerator;

    @Mock
    private QrDataFactory qrDataFactory;

    @Mock
    private QrGenerator qrGenerator;

    @Mock
    private CodeVerifier verifier;

    @InjectMocks
    private MfaService mfaService;

    @Autowired
    private MfaService realMfaService;
    @Test
    void codeGeneration_positiveTest() {
        when(secretGenerator.generate()).thenReturn("anything");

        Assertions.assertEquals("anything", mfaService.getSecret());
    }


    @Test
    void getQrCode_postiveTest() throws QrGenerationException {
        String qrCode = realMfaService.getQrCode("any_secret", "any_email");

        Assertions.assertTrue(qrCode.startsWith("data:image/png;base64"));


    }


    @Test
    void verifyCode_positiveTest() {
        String code = "some_code";
        String secret = "some_secret";

        when(verifier.isValidCode(secret, code)).thenReturn(true);

        Assertions.assertTrue(mfaService.verifyCode(secret, code));

    }
}
