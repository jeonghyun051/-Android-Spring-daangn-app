package com.cos.daangnpost2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.daangnpost2.models.ProductReqDto;

import java.io.IOException;

public class WritingActivty extends AppCompatActivity {


    final int PICK_IMAGE_MULTIPLE = 1;
    int REQUEST_EXTERNAL_STORAGE_PERMISSION = 1002;
    int REQUEST_IMAGE_CODE = 1001;

    private EditText writingEtTitle, writingEtPrice, writingEtContent;
    private static final String TAG = "WritingActivty";
    private TextView mTvCategories;
    private TextView mTvCategoryNo;
    private ImageView ivPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        writingEtTitle = findViewById(R.id.writing_et_title);
        mTvCategories = findViewById(R.id.writing_tv_categories);
        mTvCategoryNo = findViewById(R.id.writing_tv_categoryNo);
        writingEtPrice = findViewById(R.id.writing_et_price);
        writingEtContent = findViewById(R.id.writing_et_content);
        ivPost = findViewById(R.id.ivPost);
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

    /* 권한 (Manifext 에도 권한 추가) */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_writing, container, false);

        if (ContextCompat.checkSelfPermission(new WritingActivty(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
        }

        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in, REQUEST_IMAGE_CODE);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CODE){
            Uri image = data.getData();
            try {
                Bitmap bitmap  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                ivPost.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}