package views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by xilosada on 27/11/16.
 */

public class RxEditText extends EditText {

    private PublishSubject<String> subject = PublishSubject.create();

    public RxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RxEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RxEditText(Context context) {
        super(context);
        init();
    }

    public Flowable<String> getTextChanges() {return subject.toFlowable(BackpressureStrategy.LATEST);}

    private void init() {

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                subject.onNext(s.toString());
            }
        });
    }
}
