package com.cos.daangnpost2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cos.daangnpost2.models.ProductReqDto;

public class WritingActivty extends AppCompatActivity {

    final int PICK_IMAGE_MULTIPLE = 1;


    private EditText writingEtTitle, writingEtPrice, writingEtContent;
    private static final String TAG = "WritingActivty";
    private TextView mTvCategories;
    private TextView mTvCategoryNo;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        writingEtTitle = findViewById(R.id.writing_et_title);
        mTvCategories = findViewById(R.id.writing_tv_categories);
        mTvCategoryNo = findViewById(R.id.writing_tv_categoryNo);
        writingEtPrice = findViewById(R.id.writing_et_price);
        writingEtContent = findViewById(R.id.writing_et_content);

    }
    
    public void writingOnClick(View view) {
        switch (view.getId()) {
            case R.id.writing_btn_back:
                onBackPressed();
                break;
                
            case R.id.writing_btn_submit:
                submit();
                break;

            case R.id.writing_btn_categories:
                showCategories();
                break;
                
            case R.id.writing_btn_upload:
                uploadImage();
                break;
                
            default:
                break;
        }
    }

    public void uploadImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

        System.out.println("사진업로드");
    }

    public void showCategories() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WritingActivty.this);
        final String[] versionArray = new String[] {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
                "뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};

        builder.setItems(versionArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvCategories.setText(versionArray[which]);
                mTvCategoryNo.setText((which+1)+"");
            }
        });
        builder.show();
    }

    public void submit() {
        System.out.println("writingActivity submit : ");

        ProductReqDto productReqDto = new ProductReqDto();
        productReqDto.setTitle(writingEtTitle.getText().toString());
        productReqDto.setPrice(Integer.parseInt(writingEtPrice.getText().toString()));
        productReqDto.setContent(writingEtContent.getText().toString());
        productReqDto.setCategory(mTvCategories.getText().toString());
        Log.d(TAG, "submit: 모델 값 " + productReqDto);

    }
}