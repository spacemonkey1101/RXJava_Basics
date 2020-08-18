package com.example.rxjava_basics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerView;
    private static final String TAG = "MainActivity";
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCustomAdapter recyclerViewCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding to ui componend is made easy by butterknife
        //no need for find view by id
        ButterKnife.bind(this);

//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerViewCustomAdapter = new RecyclerViewCustomAdapter();
//        myRecyclerView.setLayoutManager(linearLayoutManager);
//        myRecyclerView.setAdapter(recyclerViewCustomAdapter);
//
//        //rxjava code
//        //observable is the data source
//     /*   Observable.just("How are you?").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                textView.setText(s);
//            }
//        });*/
//      //Set textview the lambda way
//      //Observable.just("Hey! There").subscribe(s -> textView.setText(s));
//        Observable.just("Hey! There").subscribe(textView::setText);
//
//       /* Observable.just("Ritam","Monica","Chandler","Walter","Thomas").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                recyclerViewCustomAdapter.addStringToList(s);
//            }
//        });*/
//
//        Entry entry1 = new Entry("PS4", BigDecimal.valueOf(1500), new Date());
//        Entry entry2 = new Entry("XBox", BigDecimal.valueOf(2000), new Date());
//        Entry entry3 = new Entry("PS5", BigDecimal.valueOf(2500), new Date());
//        Entry entry4 = new Entry("Nintendo", BigDecimal.valueOf(1000), new Date());
//
////        Observable.just(entry1,entry2,entry3,entry4).subscribe(new Consumer<Entry>() {
////            @Override
////            public void accept(Entry entry) throws Throwable {
////                recyclerViewCustomAdapter.addEntryToList(entry);
////            }
////        });
//        //set recyclerview the lambda way
//        //Observable.just(entry1,entry2,entry3,entry4).subscribe(entry -> recyclerViewCustomAdapter.addEntryToList(entry));
//        Observable.just(entry1,entry2,entry3,entry4).subscribe(recyclerViewCustomAdapter::addEntryToList);
//
//        //observable is the source of data
//        //subscribe waits for the data to arrive
//        Observable.just("HI Hello").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        });
//        //a disposable is used to control the lifecycle of an observable
//        //the data from an observable is limitless and it might crash the app
//        Disposable disposable =  Observable.just("Thank You").subscribe();//subscribe returns a disposable
//        disposable.dispose();//eliminates the observable ; eliminates the data source
//        disposable.isDisposed();//check if disposed
//        Disposable groupDisposables = new CompositeDisposable( //to dispose off a group of observables all at once
//                Observable.just("hi").subscribe(),
//                Observable.just("how").subscribe(),
//                Observable.just("are").subscribe(),
//                Observable.just("you").subscribe(),
//                Observable.just("today").subscribe()
//        );
//
//        groupDisposables.dispose();
//
//        //dispose off one by one
//        List<Disposable> disposableArrayList = new ArrayList<>();
//
//
//            disposableArrayList.add(Observable.just("Hi").subscribe());
//            disposableArrayList.add(Observable.just("How").subscribe());
//            disposableArrayList.add(Observable.just("Are").subscribe());
//            disposableArrayList.add(Observable.just("You").subscribe());
//
//
//        ;for(Disposable disposable1: disposableArrayList){
//            disposable1.dispose();
//        }
//
//        //dispos in a group
//        Disposable grDisposables = new CompositeDisposable(
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe(),
//                Observable.just("Hi").subscribe()
//        );
//        grDisposables.dispose();

        //schedulers schedules a unit of work to be executed now or later
        //they control threads to prevent blovkage of the UI thread
        //subscribeOn( is used to set the scheduler
//        Observable.just("Monica","Joey","Chander").subscribeOn(Schedulers.io()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.i("RxJAVA TAG","The accept method is executed on" + Thread.currentThread().getName() + " " + s);
//            }
//        });
        //running on main thread ;; no use of scheduler
//        Observable.just("Monica","Joey","Chander").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.i("RxJAVA TAG","The accept method is executed on" + Thread.currentThread().getName() + " " + s);
//            }
//        });
        //using lambdas
//        Observable.just("Monica","Joey")
//                .doOnNext(value -> showThreadNameStateAndValue("DoOnNext" , value))
//                .subscribe(sValue -> showThreadNameStateAndValue("Subscribe", sValue));
//
//    }
        // scheduler on lambdas
//            Observable.just("Monica","Joey")
//                    .subscribeOn(Schedulers.io())
//                    .doOnNext(value -> showThreadNameStateAndValue("DoOnNext" , value))
//            .subscribe(sValue -> showThreadNameStateAndValue("Subscribe", sValue));
//
//        Observable.just("Download an image from internet")
//                .subscribeOn(Schedulers.io())
//                .doOnNext(value -> showThreadNameStateAndValue("DoOnNext" , value))//happening on 1 Thread
//                .observeOn(AndroidSchedulers.mainThread()) // switching from 1 thread to main Thread
//                .subscribe(sValue -> showThreadNameStateAndValue("Subscribe", sValue));//happening on Main Thread

        //Observable flow
//        Observable.just("Monica","Joey")
//                .subscribeOn(Schedulers.io())
//                .doOnNext(value -> showThreadNameStateAndValue("DoOnNext" , value))
//                .doOnEach(value -> showThreadNameState("DOOnEach" ))
//                .doOnComplete(()-> showThreadNameState("DoOnComplete"))
//                .doOnTerminate(()-> showThreadNameState("DoOnTerminate"))
//                .doFinally(()-> showThreadNameState("DoFinally"))
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(value -> showThreadNameState("DoOnSubscribe" ))
//                .subscribe(value -> showThreadNameStateAndValue("Subscribe" , value));

        //Flowable : controls handling of large amount of memory
        PublishSubject<Integer> myobservable = PublishSubject.create();
        //Bad Strategy = no strategy
//        myobservable.observeOn(Schedulers.computation())
//                .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));
//

        //Missing Exception Strategy
//        myobservable.toFlowable(BackpressureStrategy.MISSING)
//              .observeOn(Schedulers.computation())
//           .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));

        //Dropping Strategy
//        myobservable.toFlowable(BackpressureStrategy.DROP)
//             .observeOn(Schedulers.computation())
//           .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));
//
        //Sample Strategy
//        myobservable.toFlowable(BackpressureStrategy.MISSING)
//                .sample(10 , TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.computation())
//                .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));

        //Latest Strategy
//        myobservable.toFlowable(BackpressureStrategy.LATEST)
//                .observeOn(Schedulers.computation())
//                .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));


        //Debounce Strategy
//        myobservable.toFlowable(BackpressureStrategy.LATEST)
//                .observeOn(Schedulers.computation())
//                .debounce(10, TimeUnit.MILLISECONDS)
//                .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));

        //Buffer Strategy
//        myobservable.toFlowable(BackpressureStrategy.BUFFER)
//                .observeOn(Schedulers.computation())
//                .subscribe(item -> showThreadNameStateAndValue("Subscribe" , "" + item));
//
//
//        for(int i = 0 ; i < 2000000 ; i++) {
//            myobservable.onNext(i);
//        }

        //Completable : Represents an action that will be done in future
//        Completable myCompletable = Completable.fromAction(() ->
//                Log.i("RXJAVA" , "Joey wants to be a boxer in 3 months"));
//
//        myCompletable.subscribe(() ->{
//            Log.i("RXJAVA" , " 3 months have passed - Joey is now a boxer");
//            showThreadNameState("subscribe");
//        }, throwable -> {
//            Log.i("RXJAVA" , throwable.toString());
//        });

        //Single only emits 1 item instead of a stream of elements like an observable
//        Single mySingle = Single.just("Michael Jackson");
//        //emit the data
//        mySingle.subscribe(mj -> {
//            Log.i("RXJAVA" , mj + " is the best");
//        }, throwable -> {
//            Log.i("RXJAVA", throwable.toString());
//        });

        //Maybe : combination of completable and single
        //it is used for an action that can complete(completable) and also return an item(single)
        Maybe.just("Ross wants a dinosaur in 3 months").subscribe(
                success -> Log.i(TAG,"Ross Ready"),
                throwable -> Log.i(TAG,"Ross Failed"),
                () ->  Log.i(TAG,"Ross Completed")
        );
    }

    public void showThreadNameStateAndValue(String state, String value){
        String des = Thread.currentThread().getName() + " Thread" + " - State: " + state + " Value: " + value;
        Log.i("RxJAVA TAG" , des);
    }

    public void showThreadNameState(String state){
        String des = Thread.currentThread().getName() + " Thread" + " - State: " + state ;
        Log.i("RxJAVA TAG" , des);
    }

}