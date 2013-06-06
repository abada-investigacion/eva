/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.model.v25.segment.OBX;
import com.abada.eva.api.EsperService;
import com.abada.eva.api.SetEsperService;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.List;


/**
 *
 * @author jesus
 */
public class CustomORUUpdateListener implements UpdateListener, SetEsperService {

    private EsperService cep;

    public void update(EventBean[] ebs, EventBean[] ebs1) {
        if (ebs != null) {
            for (EventBean e : ebs) {

                ORU_R01 oru = (ORU_R01) e.getUnderlying();
                ORU_R01Custom oruc = getCustomORU(oru);
                
                cep.send(oruc);
            }
        }
    }

    public void setEsperService(EsperService esperService) {
        cep = esperService;
    }

    public EsperService getEsperService() {
        return cep;
    }

    private ORU_R01Custom getCustomORU(ORU_R01 oru) {
        ORU_R01Custom oruc = new ORU_R01Custom();

        oruc.setMessageId(oru.getMSH().getMsh10_MessageControlID().getName());
        oruc.setNhc(getNHC(oru));
        oruc.setHemocultivo(isHemocultive(oru));


        return oruc;
    }

    private String getNHC(ORU_R01 oru) {

        CX[] pid3 = oru.getPATIENT_RESULT().getPATIENT().getPID().getPatientIdentifierList();

        for (int i = 0; i < pid3.length; i++) {

            String code = pid3[i].getCx5_IdentifierTypeCode().getValue();
            String id = pid3[i].getCx1_IDNumber().getValue();

            if (code.equals("PI")) {
                return id;
            }
        }

        return null;
    }

    private boolean isHemocultive(ORU_R01 oru) {
        try {              
            for (int i = 0; i < oru.getPATIENT_RESULT().getORDER_OBSERVATIONReps(); i++) {

                ORU_R01_ORDER_OBSERVATION obs = oru.getPATIENT_RESULT().getORDER_OBSERVATION(i);

                for (int e = 0; e < obs.getOBSERVATIONReps(); e++) {

                    OBX obx = obs.getOBSERVATION(e).getOBX();

                    String code = obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue();

                    String codeSystem = obx.getObx3_ObservationIdentifier().getCe3_NameOfCodingSystem().getValue();


                    if ((code.equals("6463-4") || code.equals("41852-5")) && codeSystem.equals("LN")) {
                        return false;
                    }

                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
