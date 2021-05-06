package ir.otoplay.qrdetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class QrDetector {
    Context context;
    private ServiceMangerReceiver serviceMangerReceiver=new ServiceMangerReceiver();
    public QrDetector(Context context) {
        this.context = context;
        IntentFilter filter=new IntentFilter();
        filter.addAction("ServiceManager.Notification");
        context.registerReceiver(serviceMangerReceiver,filter);
    }
    public OnQrCodeListener onQrCodeListener;
    public class ServiceMangerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String Action =intent.getStringExtra("Action");
            String Value =intent.getStringExtra("Value");
            if(Action.equals("QrCode")){
                if(onQrCodeListener!=null){
                   onQrCodeListener.Result(Value);
                }
            }

        }
    }
    public void setOnQrCodeListener(OnQrCodeListener onQrCodeListener){
        this.onQrCodeListener=onQrCodeListener;
    }

}
