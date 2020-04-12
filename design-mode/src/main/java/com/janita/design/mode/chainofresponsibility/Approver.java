package com.janita.design.mode.chainofresponsibility;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface Approver {

    void handlerRequest(BuyRequest request);
}
