package pe.edu.pucp.tel306.Threads;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ControladorModelView extends ViewModel {
    private MutableLiveData<Pair<Boolean,Integer>> contadorSegundos = new MutableLiveData<>(new Pair<Boolean, Integer>(true,59));
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

                            for (; contadorLocalMin <= 0; contadorLocalMin--) {
                                Log.d("contadorApp", String.valueOf(contadorLocalMin));

                                getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,contadorLocalMin));

                                for(;contadorLocalSegunfos <= 0; contadorLocalSegunfos--){
                                    getContadorSegundos().postValue(new Pair<Boolean, Integer>(true,contadorLocalSegunfos));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        break;
                                    }

                                    if(contadorLocalSegunfos == -1){
                                        getContadorMinutos().postValue(new Pair<Boolean, Integer>(false,59));
                                        break;
                                    }

                                }

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            setThread(null);
                            if(contadorLocalMin == -1 ) {
                                getContadorMinutos().postValue(new Pair<Boolean, Integer>(false,4));
                            }


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

                    for (; contadorLocalMin <= 0; contadorLocalMin--) {
                        Log.d("contadorApp", String.valueOf(contadorLocalMin));

                        getContadorMinutos().postValue(new Pair<Boolean, Integer>(true,contadorLocalMin));

                        for(;contadorLocalSegunfos <= 0; contadorLocalSegunfos--){
                            getContadorSegundos().postValue(new Pair<Boolean, Integer>(true,contadorLocalSegunfos));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                break;
                            }

                            if(contadorLocalSegunfos == -1){
                                getContadorMinutos().postValue(new Pair<Boolean, Integer>(false,59));
                                break;
                            }

                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    setThread(null);
                    if(contadorLocalMin == -1 ) {
                        getContadorMinutos().postValue(new Pair<Boolean, Integer>(false,4));
                    }


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
}
