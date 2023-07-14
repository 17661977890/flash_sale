package cn.wolfcode.service;

import cn.wolfcode.domain.OperateIntergralVo;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Created by lanxw
 */
@LocalTCC
public interface IUsableIntegralService {
    /**
     * 增加积分 TCC
     */
    @TwoPhaseBusinessAction(name = "decrIntergralTry", commitMethod = "decrIntergralCommit", rollbackMethod = "decrIntergralRollback")
    void decrIntergralTry(@BusinessActionContextParameter(paramName = "operateIntergralVo") OperateIntergralVo operateIntergralVo, BusinessActionContext context);

    void decrIntergralCommit(BusinessActionContext context);

    void decrIntergralRollback(BusinessActionContext context);

    /**
     * 增加积分
     * @param vo
     */
    void decrIntergral(OperateIntergralVo vo);

    /**
     * 扣减积分
     * @param vo
     */
    void incrIntergral(OperateIntergralVo vo);
}
