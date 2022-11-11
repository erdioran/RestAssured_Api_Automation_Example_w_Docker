
package com.erdioran.PojoJson.Payout;


public class PostPayout {


    private String externalId;

    private String senderId;

    private String beneficiaryId;

    private PayoutMethod payoutMethod;

    private Amount amount;

    private Meta meta;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public PayoutMethod getPayoutMethod() {
        return payoutMethod;
    }

    public void setPayoutMethod(PayoutMethod payoutMethod) {
        this.payoutMethod = payoutMethod;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
