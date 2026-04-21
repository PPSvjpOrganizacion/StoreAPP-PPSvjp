package es.storeapp.business.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BizumPaymentService {

    public BizumPaymentResult processBizumPayment(String phoneNumber, BigDecimal amount, String concept) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return new BizumPaymentResult(false, "Número de teléfono no válido", null, LocalDateTime.now());
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new BizumPaymentResult(false, "Importe no válido", null, LocalDateTime.now());
        }

        String operationId = "BIZ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        System.out.println("Iniciando pago por Bizum...");
        System.out.println("Teléfono: " + phoneNumber);
        System.out.println("Importe: " + amount + " EUR");
        System.out.println("Concepto: " + concept);
        System.out.println("Operación generada: " + operationId);

        return new BizumPaymentResult(
                true,
                "Pago Bizum simulado correctamente",
                operationId,
                LocalDateTime.now()
        );
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^[0-9]{8}$");
    }

    public static class BizumPaymentResult {
        private final boolean success;
        private final String message;
        private final String operationId;
        private final LocalDateTime timestamp;

        public BizumPaymentResult(boolean success, String message, String operationId, LocalDateTime timestamp) {
            this.success = success;
            this.message = message;
            this.operationId = operationId;
            this.timestamp = timestamp;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getOperationId() {
            return operationId;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "BizumPaymentResult{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    ", operationId='" + operationId + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
