import java.util.Calendar;

public class Data {
    int dia;
    int mes;
    int ano;
    int horas;
    int minutos;

    public Data(){
        Calendar calendario = Calendar.getInstance();
        this.dia = calendario.get(Calendar.DAY_OF_MONTH);
        this.mes = calendario.get(Calendar.MONTH);
        this.setMes(this.getMes()+1);
        this.ano = calendario.get(Calendar.YEAR);
        this.horas = calendario.get(Calendar.HOUR_OF_DAY);
        this.minutos = calendario.get(Calendar.MINUTE);
    }

    public Data(int dia, int mes, int ano, int horas, int minutos) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.horas = horas;
        this.minutos = minutos;
    }

    public boolean comparaDatas(Data dAgora, Data dEleicaoInicio, Data dEleicaoFim) {
        if(dAgora.getAno() >= dEleicaoInicio.getAno() && dAgora.getAno() <= dEleicaoFim.getAno()){
            if(dEleicaoInicio.getAno() == dEleicaoFim.getAno()){
                if(dAgora.getMes() >= dEleicaoInicio.getMes() && dAgora.getMes() <= dEleicaoFim.getMes()){
                    if(dEleicaoInicio.getMes() == dEleicaoFim.getMes()){
                        if(dAgora.getDia() >= dEleicaoInicio.getDia() && dAgora.getDia() <= dEleicaoFim.getDia()){
                            if(dEleicaoInicio.getDia() == dEleicaoFim.getDia()){
                                if(dAgora.getHoras() >= dEleicaoInicio.getHoras() && dAgora.getHoras() <= dEleicaoFim.getHoras()){
                                    if(dEleicaoInicio.getHoras() == dEleicaoFim.getHoras()){
                                        if(dAgora.getMinutos() >= dEleicaoInicio.getMinutos() && dAgora.getMinutos() <= dEleicaoFim.getMinutos()){
                                            return true;
                                        }
                                        else return false;
                                    }
                                    else{
                                        if(dAgora.getHoras() == dEleicaoInicio.getHoras()){
                                            if(dAgora.getMinutos() >= dEleicaoInicio.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else if(dAgora.getHoras() == dEleicaoFim.getHoras()){
                                            if(dAgora.getMinutos() <= dEleicaoFim.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                }
                                else return false;
                            }
                            else{
                                if(dAgora.getDia() == dEleicaoInicio.getDia()){
                                    if(dAgora.getHoras() >= dEleicaoInicio.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoInicio.getHoras()){
                                            if(dAgora.getMinutos() >= dEleicaoInicio.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else if(dAgora.getDia() == dEleicaoFim.getDia()){
                                    if(dAgora.getHoras() <= dEleicaoFim.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoFim.getHoras()){
                                            if(dAgora.getMinutos() <= dEleicaoFim.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                        }
                        else return false;
                    }
                    else{
                        if(dAgora.getMes() == dEleicaoInicio.getMes()){
                            if(dAgora.getDia() >= dEleicaoInicio.getDia()){
                                if(dAgora.getDia() == dEleicaoInicio.getDia()){
                                    if(dAgora.getHoras() >= dEleicaoInicio.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoInicio.getHoras()){
                                            if(dAgora.getMinutos() >= dEleicaoInicio.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else if(dAgora.getMes() == dEleicaoFim.getMes()){
                            if(dAgora.getDia() <= dEleicaoFim.getDia()){
                                if(dAgora.getDia() == dEleicaoFim.getDia()){
                                    if(dAgora.getHoras() <= dEleicaoFim.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoFim.getHoras()){
                                            if(dAgora.getMinutos() <= dEleicaoFim.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                }
                else return false;
            }
            else{
                if(dAgora.getAno() == dEleicaoInicio.getAno()){
                    if(dAgora.getMes() >= dEleicaoInicio.getMes()){
                        if(dAgora.getMes() == dEleicaoInicio.getMes()){
                            if(dAgora.getDia() >= dEleicaoInicio.getDia()){
                                if(dAgora.getDia() == dEleicaoInicio.getDia()){
                                    if(dAgora.getHoras() >= dEleicaoInicio.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoInicio.getHoras()){
                                            if(dAgora.getMinutos() >= dEleicaoInicio.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else if(dAgora.getAno() == dEleicaoFim.getAno()){
                    if(dAgora.getAno() <= dEleicaoFim.getMes()){
                        if(dAgora.getMes() == dEleicaoFim.getMes()){
                            if(dAgora.getDia() <= dEleicaoFim.getDia()){
                                if(dAgora.getDia() == dEleicaoFim.getDia()){
                                    if(dAgora.getHoras() <= dEleicaoFim.getHoras()){
                                        if(dAgora.getHoras() == dEleicaoFim.getHoras()){
                                            if(dAgora.getMinutos() <= dEleicaoFim.getMinutos()){
                                                return true;
                                            }
                                            else return false;
                                        }
                                        else return true;
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
        }
        else return false;
    }

    public boolean validaDatas(Data agora, Data validade) {
        if(agora.getAno() >= validade.getAno()) {
            if(agora.getAno() == validade.getAno()) {
                if(agora.getMes() >= validade.getMes()) {
                    if(agora.getMes() == validade.getMes()) {
                        if(agora.getDia() >= validade.getDia()){
                            if(agora.getDia() == validade.getDia()){
                                return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else return true;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "Data {" + dia + "/" + mes + "/" + ano + ", " + horas + ":" + minutos + '}';
    }
}
