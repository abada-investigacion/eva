/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.generator.domain;

import com.abada.generator.object.Patient;
import com.abada.generator.enums.Messages;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.abada.generator.enums.OrderControl;
import com.abada.generator.object.Order;
import com.abada.generator.object.OrderTiming;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author david
 */
public class Domain {

    private static Log log = LogFactory.getLog(Domain.class);
    protected Properties property;

    public Domain(Properties property) {
        this.property = property;

    }

    /**
     * select que obtiene los datos de los pacientes de bd, y los añada a un
     * objeto
     *
     * @param codigoSQL
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void load(List lmessage, Messages adt) throws Exception {

        SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!adt.equals(Messages.OMP)) {
            Patient Patient;

            Patient = new Patient();
            String numerohc[] = property.getProperty("numerohc").split(",");
            Patient.setNumerohc(Integer.parseInt(numerohc[(int) (Math.random() * numerohc.length)]));

            if (adt.equals(adt.A17)) {
                String patientswapnumerohc[] = property.getProperty("patientswapnumerohc").split(",");
                Patient.setPatientswapnumerohc(Integer.parseInt(patientswapnumerohc[(int) (Math.random() * patientswapnumerohc.length)]));
                String patientswapcama[] = property.getProperty("patientswapcama").split(",");
                Patient.setPatientswapcama(patientswapcama[(int) (Math.random() * patientswapcama.length)]);

            }
            if (adt.equals(adt.A47)) {
                String numerohcanterior[] = property.getProperty("numerohcanterior").split(",");

                Patient.setNumerohcanterior(Integer.parseInt(numerohcanterior[(int) (Math.random() * numerohcanterior.length)]));
                String numerohcActual[] = property.getProperty("numerohcActual").split(",");

                Patient.setNumerohcActual(numerohcActual[(int) (Math.random() * numerohcActual.length)]);

            }
            mshAdt(Patient, adt);
            String cod_centro[] = property.getProperty("cod_centro").split(",");
            Patient.setCod_centro(Integer.parseInt(cod_centro[(int) (Math.random() * cod_centro.length)]));
            String cip[] = property.getProperty("cip").split(",");
            Patient.setCip(cod_centro[(int) (Math.random() * cod_centro.length)]);
            String numeroseguridadSocial[] = property.getProperty("numeroseguridadSocial").split(",");
            Patient.setNumeroseguridadSocial(numeroseguridadSocial[(int) (Math.random() * numeroseguridadSocial.length)]);
            String apellid1[] = property.getProperty("apellid1").split(",");
            Patient.setApellid1(apellid1[(int) (Math.random() * apellid1.length)]);
            String nombre[] = property.getProperty("nombre").split(",");
            Patient.setNombre(nombre[(int) (Math.random() * nombre.length)]);
            String apellid2[] = property.getProperty("apellid2").split(",");
            Patient.setApellid2(apellid2[(int) (Math.random() * apellid2.length)]);
            String cod_pais[] = property.getProperty("cod_pais").split(",");
            Patient.setCod_pais(cod_pais[(int) (Math.random() * cod_pais.length)]);
            String cod_postal[] = property.getProperty("cod_postal").split(",");
            Patient.setCod_postal(cod_postal[(int) (Math.random() * cod_postal.length)]);
            String direccion[] = property.getProperty("direccion").split(",");
            Patient.setDireccion(direccion[(int) (Math.random() * direccion.length)]);
            String fechanac[] = property.getProperty("fechanac").split(",");
            Patient.setFechanac(sform.parse(fechanac[(int) (Math.random() * fechanac.length)] + " 00:00:00"));
            String cod_poblacion[] = property.getProperty("cod_poblacion").split(",");
            Patient.setCod_poblacion(Integer.parseInt(cod_poblacion[(int) (Math.random() * cod_poblacion.length)]));
            String Provincia[] = property.getProperty("provincia").split(",");
            Patient.setProvincia(Provincia[(int) (Math.random() * Provincia.length)]);
            String nifdni[] = property.getProperty("nifdni").split(",");
            Patient.setNifdni(nifdni[(int) (Math.random() * nifdni.length)]);
            String paciente_ncama[] = property.getProperty("paciente_ncama").split(",");
            Patient.setPaciente_ncama(paciente_ncama[(int) (Math.random() * paciente_ncama.length)]);
            String sexo[] = property.getProperty("sexo").split(",");
            Patient.setSexo(sexo[(int) (Math.random() * sexo.length)]);
            String telefono[] = property.getProperty("telefono").split(",");
            Patient.setTelefono(telefono[(int) (Math.random() * telefono.length)]);
            lmessage.add(Patient);
        } else {
            String orderidanterior = "";

            List<OrderTiming> lOrderTiming = new ArrayList();
            OrderTiming OrderTiming = new OrderTiming();

            Order Order = new Order();
            String ORDERID[] = property.getProperty("ORDERID").split(",");
            String orderID = ORDERID[(int) (Math.random() * ORDERID.length)];
            String PRE_PAUT_HORA[] = property.getProperty("PRE_PAUT_HORA").split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse("1970-01-01 " + PRE_PAUT_HORA[(int) (Math.random() * PRE_PAUT_HORA.length)] + ":00");

            Timestamp prepautHora = new Timestamp(date.getTime());
            if (!orderID.equals(orderidanterior)) {
                if (!lOrderTiming.isEmpty()) {
                    Order.setOrderTimings(lOrderTiming);
                }
                if (Order.getOrderid() != null) {//añadimos la order a la lista
                    lmessage.add(Order);
                }
                Order = new Order();
                Order.setMessageCode("OMP");
                Order.setTriggerEvent("O09");
                OrderControl control[] = OrderControl.values();
                Order.setControl(control[(int) (Math.random() * control.length)].toString());
                Order.setOrderid(orderID);
                String COD_CENTRO[] = property.getProperty("COD_CENTRO").split(",");
                Order.setCod_centro(Integer.parseInt(COD_CENTRO[(int) (Math.random() * COD_CENTRO.length)]));

                String NUMEROHC[] = property.getProperty("NUMEROHC").split(",");
                Order.setNumerohc(Integer.parseInt(NUMEROHC[(int) (Math.random() * NUMEROHC.length)]));
                String NPROCESO[] = property.getProperty("NPROCESO").split(",");
                Order.setNproceso(Integer.parseInt(NPROCESO[(int) (Math.random() * NPROCESO.length)]));
                String LINEA[] = property.getProperty("LINEA").split(",");
                Order.setLinea(Integer.parseInt(LINEA[(int) (Math.random() * LINEA.length)]));
                String LOGIN_USU[] = property.getProperty("LOGIN_USU").split(",");
                Order.setLogin_usu(LOGIN_USU[(int) (Math.random() * LOGIN_USU.length)]);
                String FECHA_PRES[] = property.getProperty("FECHA_PRES").split(",");
                Order.setFecha_pres(sdf.parse(FECHA_PRES[(int) (Math.random() * FECHA_PRES.length)] + " 00:00:00"));
                String COD_MEDICO[] = property.getProperty("COD_MEDICO").split(",");
                Order.setCod_medico(Integer.parseInt(COD_MEDICO[(int) (Math.random() * COD_MEDICO.length)]));
                String FECHA_INI[] = property.getProperty("FECHA_INI").split(",");
                Order.setFecha_ini(sdf.parse(FECHA_INI[(int) (Math.random() * FECHA_INI.length)] + " 00:00:00"));
                String LT_PAUTAS_HORAS_DURACION[] = property.getProperty("LT_PAUTAS_HORAS_DURACION").split(",");
                Order.setHora_duracion(Integer.parseInt(LT_PAUTAS_HORAS_DURACION[(int) (Math.random() * LT_PAUTAS_HORAS_DURACION.length)]));
                String FECHA_FIN[] = property.getProperty("FECHA_FIN").split(",");
                Order.setFecha_fin(sdf.parse(FECHA_FIN[(int) (Math.random() * FECHA_FIN.length)] + " 00:00:00"));
                String DOSIS_PA[] = property.getProperty("DOSIS_PA").split(",");
                Order.setGive_amount(Double.parseDouble(DOSIS_PA[(int) (Math.random() * DOSIS_PA.length)]));
                String LT_PAUTAS_DESCRIPCION[] = property.getProperty("LT_PAUTAS_DESCRIPCION").split(",");
                Order.setInstruccion(LT_PAUTAS_DESCRIPCION[(int) (Math.random() * LT_PAUTAS_DESCRIPCION.length)]);

                String REPETIR[] = property.getProperty("REPETIR").split(",");
                Order.setRepetition_pattern(REPETIR[(int) (Math.random() * REPETIR.length)]);
                String PRECISA[] = property.getProperty("PRECISA").split(",");
                Order.setIf_necesary(PRECISA[(int) (Math.random() * PRECISA.length)]);
            } //If cod_pauta != null => repetition pattern = repetition_pattern AND if necesary = if_necesary
            else {
                String REPETITION_PATTERN[] = property.getProperty("REPETITION_PATTERN").split(",");
                Order.setRepetition_pattern(REPETITION_PATTERN[(int) (Math.random() * REPETITION_PATTERN.length)]);
                String IF_NECESARY[] = property.getProperty("IF_NECESARY").split(",");
                Order.setIf_necesary(IF_NECESARY[(int) (Math.random() * IF_NECESARY.length)]);
            }
            String ID_MEASURE_UNIT[] = property.getProperty("ID_MEASURE_UNIT").split(",");
            Order.setId_measure_unit(Integer.parseInt(ID_MEASURE_UNIT[(int) (Math.random() * ID_MEASURE_UNIT.length)]));
            String measure_unit_en_mambrino[] = property.getProperty("measure_unit_en_mambrino").split(",");
            Order.setMeasure_unit_en_mambrino(measure_unit_en_mambrino[(int) (Math.random() * measure_unit_en_mambrino.length)]);
            String ALERGIA_S_N[] = property.getProperty("ALERGIA_S_N").split(",");
            Order.setAlergia_s_n(ALERGIA_S_N[(int) (Math.random() * ALERGIA_S_N.length)]);
            String modo_prescripcion[] = property.getProperty("modo_prescripcion").split(",");
            Order.setModo_prescripcion(modo_prescripcion[(int) (Math.random() * modo_prescripcion.length)]);
            String cod_practivo[] = property.getProperty("cod_practivo").split(",");
            Order.setCod_principioActivo(Integer.parseInt(cod_practivo[(int) (Math.random() * cod_practivo.length)]));
            String COD_VIA[] = property.getProperty("COD_VIA").split(",");
            Order.setCod_via(Integer.parseInt(COD_VIA[(int) (Math.random() * COD_VIA.length)]));
            String PRESCRIP_DISP_ARTICULO[] = property.getProperty("PRESCRIP_DISP_ARTICULO").split(",");
            Order.setCod_articulo(PRESCRIP_DISP_ARTICULO[(int) (Math.random() * PRESCRIP_DISP_ARTICULO.length)]);
            String OBS_DISPENSA[] = property.getProperty("OBS_DISPENSA").split(",");
            Order.setObs_dispensa(OBS_DISPENSA[(int) (Math.random() * OBS_DISPENSA.length)]);
            String OBS_ENFERME[] = property.getProperty("OBS_ENFERME").split(",");
            Order.setObs_enferme(OBS_ENFERME[(int) (Math.random() * OBS_ENFERME.length)]);
            String OBS_MEDICO[] = property.getProperty("OBS_MEDICO").split(",");
            Order.setObs_medico(OBS_MEDICO[(int) (Math.random() * OBS_MEDICO.length)]);;
            String telL1_SECUENC_DESCRIPCIONefono[] = property.getProperty("L1_SECUENC_DESCRIPCION").split(",");
            Order.setDescripcion_secuencia(telL1_SECUENC_DESCRIPCIONefono[(int) (Math.random() * telL1_SECUENC_DESCRIPCIONefono.length)]);
            String FH_ULT_MOD[] = property.getProperty("FH_ULT_MOD").split(",");
            Order.setFh_ult_mod(sdf.parse(FH_ULT_MOD[(int) (Math.random() * FH_ULT_MOD.length)] + " 00:00:00"));

            if (prepautHora != null) {
                lOrderTiming = new ArrayList();

                orderidanterior = ORDERID[(int) (Math.random() * ORDERID.length)];
            }

            if (prepautHora != null) {
                OrderTiming = new OrderTiming();
                OrderTiming.setOrderid(orderID);
                String PRE_PAUT_LINEA_DISP[] = property.getProperty("PRE_PAUT_LINEA_DISP").split(",");
                OrderTiming.setPre_paut_linea_disp(Integer.parseInt(PRE_PAUT_LINEA_DISP[(int) (Math.random() * PRE_PAUT_LINEA_DISP.length)]));
                date = sdf.parse("1970-01-01 " + PRE_PAUT_HORA[(int) (Math.random() * PRE_PAUT_HORA.length)] + ":00");
                OrderTiming.setPre_paut_hora(new Timestamp(date.getTime()));
                lOrderTiming.add(OrderTiming);
            }

            if (Order.getOrderid() != null) {//añadimos el ultimo
                if (!lOrderTiming.isEmpty()) {
                    Order.setOrderTimings(lOrderTiming);
                }
                lmessage.add(Order);
            } else {
                lmessage.add(Order);
            }
        }
    }

    /**
     * añadimos segun el adt su codigo, triger y estructura del segmento msh
     *
     * @param patient
     * @param adt
     */
    private void mshAdt(Patient patient, Messages adt) {
        if (adt.equals(adt.A01)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A01");
            patient.setMessageStructure("ADT_A01");

        } else if (adt.equals(adt.A02)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A02");
            patient.setMessageStructure("ADT_A01");

        } else if (adt.equals(adt.A03)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A03");
            patient.setMessageStructure("ADT_A03");

        } else if (adt.equals(adt.A17)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A17");
            patient.setMessageStructure("ADT_A17");

        } else if (adt.equals(adt.A28)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A28");
            patient.setMessageStructure("ADT_A05");

        } else if (adt.equals(adt.A29)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A29");
            patient.setMessageStructure("ADT_A21");

        } else if (adt.equals(adt.A31)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A31");
            patient.setMessageStructure("ADT_A05");
        } else if (adt.equals(adt.A47)) {
            patient.setMessageCode("ADT");
            patient.setTriggerEvent("A47");
            patient.setMessageStructure("ADT_A30");

        }
    }
}
