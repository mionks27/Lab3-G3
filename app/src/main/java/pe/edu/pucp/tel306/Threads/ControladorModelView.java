package pe.edu.pucp.tel306.Threads;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ControladorModelView extends ViewModel {

    private MutableLiveData<Integer> contadorSegundos = new MutableLiveData<>();
    private MutableLiveData<Integer> contadorMinutos = new MutableLiveData<>();

    private Thread thread = null;


    public void iniciarciclo (final boolean pause){
        setThread(new Thread(new Runnable() {
            @Override
            public void run() {
                int contadorLocalMinutos = 24;
                int contadorLocalSegundos = 59;
                while (contadorLocalMinutos > 0){
                   if (pause == false){
                       contadorMinutos.postValue(contadorLocalMinutos);
                       while (contadorLocalSegundos >= 0){
                           contadorSegundos.postValue(contadorLocalSegundos);
                           contadorLocalSegundos = contadorLocalSegundos -1;
                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                               break;
                           }
                           if(contadorLocalSegundos == 0){
                               contadorLocalSegundos = 59;
                               break;
                           }
                       }

                       contadorLocalMinutos = contadorLocalMinutos -1;
                       try {
                           Thread.sleep(1000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                           break;
                       }

                       if(contadorLocalMinutos == 0){
                           contadorLocalMinutos = 24;
                           break;
                       }
                   }

                }

            }
        }));

        getThread().start();
    }

    public  void iniciarDescanso(){

    }

    public MutableLiveData<Integer> getContadorSegundos() {
        return contadorSegundos;
    }

    public void setContadorSegundos(MutableLiveData<Integer> contadorSegundos) {
        this.contadorSegundos = contadorSegundos;
    }

    public MutableLiveData<Integer> getContadorMinutos() {
        return contadorMinutos;
    }

    public void setContadorMinutos(MutableLiveData<Integer> contadorMinutos) {
        this.contadorMinutos = contadorMinutos;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
