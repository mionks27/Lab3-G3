package pe.edu.pucp.tel306.Threads;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ControladorModelView extends ViewModel {
    private MutableLiveData<Pair<Boolean,Integer>> contadorSegundos = new MutableLiveData<>(new Pair<Boolean, Integer>(true,59));
    private MutableLiveData<Pair<Boolean,String>> notificacionFin = new MutableLiveData<>(new Pair<Boolean, String>(false,"Fin del trabajo, empieza el recreo"));
    private MutableLiveData<Pair<Boolean,String>> notificacionCiclo = new MutableLiveData<>(new Pair<Boolean, String>(false,"Ciclo pomodoro 1 de 4"));
    private MutableLiveData<Pair<Boolean,Integer>> contadorMinutos = new MutableLiveData<>(new Pair<Boolean, Integer>(true,24));
    private Thread thread = null;
    public  void iniciarContador(){
        if (getThread() == null) {

            setThread(new Thread(new Runnable() {
                @Override
                public void run() {
                    Pair<Boolean,Integer> valorSegundos = getContadorSegundos().getValue();
                    int contadorLocalSegunfos = valorSegundos.second;

                    Pair<Boolean,Integer> valorMin= getContadorMinutos().getValue();
                    int contadorLocalMin= valorMin.second;

                    for(int i = 1;i<=4;i++){
                        getNotificacionCiclo().postValue(new Pair<Boolean, String>(true,"Ciclo pomodoro"+ i +"de 4"));
                        for (; contadorLocalMin >= 0; contadorLocalMin--) {
                            getNotificacionFin().postValue(new Pair<Boolean, String>(false,"Fin del trabajo, empieza el recreo"));

                            getNotificacionFin().postValue(new Pair<Boolean, String>(false,"xsdsdsdsdsdsds"));

                            Log.d("contadorApp", String.valueOf(contadorLocalMin));
                            getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,contadorLocalMin));
                            for(;contadorLocalSegunfos >= 0; contadorLocalSegunfos--){
                                Log.d("contadorApp2", String.valueOf(contadorLocalSegunfos));
                                getContadorSegundos().postValue(new Pair<Boolean, Integer>(true,contadorLocalSegunfos));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }
                                if(contadorLocalSegunfos == 0){
                                    contadorLocalSegunfos = 59;
                                    break;
                                }
                            }
                        }
                        getNotificacionFin().postValue(new Pair<Boolean, String>(true,"Fin del trabajo, empieza el recreo"));

                        if(contadorLocalMin == 0) {
                            contadorLocalMin = 4;
                            getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,4));
                        }


                    }

                    setThread(null);
                }
            }));

            getThread().start();
        }


    }

    public void contadorDescanso(){
        if (getThread() == null) {

            setThread(new Thread(new Runnable() {
                @Override
                public void run() {
                    Pair<Boolean,Integer> valorSegundos = getContadorSegundos().getValue();
                    int contadorLocalSegunfos = valorSegundos.second;

                    Pair<Boolean,Integer> valorMin= getContadorMinutos().getValue();
                    int contadorLocalMin= valorMin.second;

                    for (; contadorLocalMin >= 0; contadorLocalMin--) {
                        Log.d("contadorApp", String.valueOf(contadorLocalMin));

                        getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,contadorLocalMin));

                        for(;contadorLocalSegunfos >= 0; contadorLocalSegunfos--){
                            Log.d("contadorApp2", String.valueOf(contadorLocalSegunfos));
                            getContadorSegundos().postValue(new Pair<Boolean, Integer>(true,contadorLocalSegunfos));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                break;
                            }
                            if(contadorLocalSegunfos == 0){
                                contadorLocalSegunfos = 59;
                                break;
                            }
                        }
                    }
                    if(contadorLocalMin == 0) {
                        getContadorMinutos().postValue(new Pair<Boolean, Integer>(false,4));
                    }
                    setThread(null);
                }
            }));
            getThread().start();
        }

    }

    public void refreshContador(){
        if(getThread() != null){
            getThread().interrupt();
            getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,24));
            getContadorSegundos().postValue(new Pair<Boolean, Integer>(true,59));
        }
    }

    public void pauseContador(){
        if(getThread() != null){
            getThread().interrupt();
        }
    }

    public MutableLiveData<Pair<Boolean, Integer>> getContadorSegundos() {
        return contadorSegundos;
    }

    public void setContadorSegundos(MutableLiveData<Pair<Boolean, Integer>> contadorSegundos) {
        this.contadorSegundos = contadorSegundos;
    }

    public MutableLiveData<Pair<Boolean, Integer>> getContadorMinutos() {
        return contadorMinutos;
    }

    public void setContadorMinutos(MutableLiveData<Pair<Boolean, Integer>> contadorMinutos) {
        this.contadorMinutos = contadorMinutos;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public MutableLiveData<Pair<Boolean, String>> getNotificacionFin() {
        return notificacionFin;
    }

    public void setNotificacionFin(MutableLiveData<Pair<Boolean, String>> notificacionFin) {
        this.notificacionFin = notificacionFin;
    }

    public MutableLiveData<Pair<Boolean, String>> getNotificacionCiclo() {
        return notificacionCiclo;
    }

    public void setNotificacionCiclo(MutableLiveData<Pair<Boolean, String>> notificacionCiclo) {
        this.notificacionCiclo = notificacionCiclo;
    }
}
