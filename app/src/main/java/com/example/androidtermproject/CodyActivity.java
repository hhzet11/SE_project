package com.example.androidtermproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodyActivity extends AppCompatActivity {

    private Uri mImageCaptureUri;
    private ImageView iv_UserPhoto;
    private String absolutePath;
    private ProgressDialog uploading;
    private File file;

    String using_category;
    String using_detail;
    String using_color;
    String using_style;

    String mCurrentPhotoPath;

    /////////////////////////////////////////카메라에서 찍어서 올리기

    public void capture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.androidtermproject.fileProvider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 2);
                Toast.makeText(getApplicationContext(),"phtoURL : "+photoURI,Toast.LENGTH_LONG).show();
            }
        }
    }


    ///////////////////////////////////사진 앨범에서 선택해서 올리기
    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);

    }

    String[] category = {"(선택)","상의","하의","신발","아우터","즐겨찾기"};
    
    String[] detail_top = {"(선택)","반팔","맨투맨","니트","셔츠","남방","후드티","기타"};
    String[] detail_pants = {"(선택)","반바지","청바지","면바지","슬랙스","스키니","와이드","기타"};
    String[] detail_shoes = {"(선택)","슬리퍼","샌들","컨버스","운동화","구두","기타"};
    String[] detail_outer = {"(선택)","후드집업","가디건","자켓","야상","코트","패딩","기타"};
    String[] string_favorite = {"(전체)"};
    String[] detail_not_selected = {"옷 종류(x)"};

    String[] color = {"(선택)","빨강색","주황색","노랑색","초록색","파랑색","남색","보라색","흰색","검은색","기타"};
    String[] style = {"(선택)","일상","운동","격식","데이트"};
    Button change;
    Button done;
    Button reset;
    private View layoutCody;
    private View layoutCloth;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cody);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");

        final Spinner category_spinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(adapter);

        final Spinner detail_spinner = (Spinner)findViewById(R.id.detail_spinner);

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String str_top = "상의";
                String str_pants = "하의";
                String str_shoes = "신발";
                String str_outer = "아우터";
                String str_favorite = "즐겨찾기";
                String str_not_selected = "(선택)";

                if(str_favorite.equals(category_spinner.getSelectedItem().toString())){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getBaseContext(), android.R.layout.simple_spinner_item, string_favorite);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    detail_spinner.setAdapter(adapter);

                    Spinner color_spinner = (Spinner) findViewById(R.id.color_spinner);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                            getBaseContext(), android.R.layout.simple_spinner_item, string_favorite);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    color_spinner.setAdapter(adapter1);

                    Spinner style_spinner = (Spinner) findViewById(R.id.style_spinner);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                            getBaseContext(), android.R.layout.simple_spinner_item, string_favorite);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    style_spinner.setAdapter(adapter2);

                }

                else{

                    if(str_top.equals(category_spinner.getSelectedItem().toString())){
                        using_category = "상의";
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getBaseContext(), android.R.layout.simple_spinner_item, detail_top);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        detail_spinner.setAdapter(adapter);

                        detail_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String detail;
                                using_detail = detail_spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                    else if(str_pants.equals(category_spinner.getSelectedItem().toString())){
                        using_category="하의";
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getBaseContext(), android.R.layout.simple_spinner_item, detail_pants);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        detail_spinner.setAdapter(adapter);

                        detail_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String detail;
                                using_detail = detail_spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                    else if(str_shoes.equals(category_spinner.getSelectedItem().toString())){
                        using_category="신발";
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getBaseContext(), android.R.layout.simple_spinner_item, detail_shoes);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        detail_spinner.setAdapter(adapter);

                        detail_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String detail;
                                using_detail = detail_spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                    else if(str_outer.equals(category_spinner.getSelectedItem().toString())){
                        using_category="아우터";
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getBaseContext(), android.R.layout.simple_spinner_item, detail_outer);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        detail_spinner.setAdapter(adapter);
                        detail_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String detail;
                                using_detail = detail_spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    else if (str_not_selected.equals(category_spinner.getSelectedItem().toString())) {
                        //아무것도 선택이 안되어 있을 경우
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getBaseContext(), android.R.layout.simple_spinner_item, detail_not_selected);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        detail_spinner.setAdapter(adapter);
                    }

                    final Spinner color_spinner = (Spinner) findViewById(R.id.color_spinner);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                            getBaseContext(), android.R.layout.simple_spinner_item, color);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    color_spinner.setAdapter(adapter1);

                    color_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String color;
                            using_color = color_spinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });



                    final Spinner style_spinner = (Spinner) findViewById(R.id.style_spinner);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                            getBaseContext(), android.R.layout.simple_spinner_item, style);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    style_spinner.setAdapter(adapter2);

                    style_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String style;
                            using_style = style_spinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        layoutCody = (LinearLayout)findViewById(R.id.codyImg_layout);
        layoutCloth = (RelativeLayout)findViewById(R.id.cloth_layout);

        change = (Button)findViewById(R.id.change_btn);
        done = (Button)findViewById(R.id.done_btn);
        reset = (Button)findViewById(R.id.reset_btn);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),""+using_category+"/"+using_detail+"/"+using_color+"/"+using_style,Toast.LENGTH_SHORT).show();
                if(layoutCody.getVisibility() == View.VISIBLE){
                    layoutCloth.setVisibility(View.VISIBLE);
                    layoutCody.setVisibility(View.INVISIBLE);
                    change.setText(R.string.change_btn1_2);

                }
                else{
                    layoutCloth.setVisibility(View.INVISIBLE);
                    layoutCody.setVisibility(View.VISIBLE);
                    change.setText(R.string.change_btn1_1);
                }
            }
        });

        GridView cloth_grid = findViewById(R.id.cloth_grid);

        cloth_grid.setAdapter(new ClothAdapter(this));

        final ImageView cloth_top_image = (ImageView)findViewById(R.id.cloth_top_image);
        final ImageView cloth_pants_image = (ImageView)findViewById(R.id.cloth_pants_image);
        final ImageView shoes_image = (ImageView)findViewById(R.id.shoes_image);

        ///옷 리스트 어레이
        final TypedArray cloth_top_list = getResources().obtainTypedArray(R.array.cloth_list);

        //옷 카테고리 string
        final String str_category = (String)category_spinner.getSelectedItem();

        cloth_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"clicked position : " + i,Toast.LENGTH_SHORT).show();

                    cloth_top_image.setImageResource(cloth_top_list.getResourceId(i,-1));

            }
        });

        ////////되돌리기
        Button back_state = (Button)findViewById(R.id.go_back_state);

        back_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cloth_top_image.setImageResource(0);
                cloth_pants_image.setImageResource(0);
                shoes_image.setImageResource(0);
            }
        });

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.select_picture);
        linearLayout.setVisibility(View.INVISIBLE);

        Button change_face = (Button)findViewById(R.id.change_face);

        change_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout.setVisibility(View.VISIBLE);

            }
        });

        //////////////////////////////// 사진 촬영 버튼

        Button btn_camera = (Button)findViewById(R.id.btn_camera);


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                capture();

            }
        });


        ////////////////////////////////앨범 선택 버튼

        Button btn_album = (Button) findViewById(R.id.btn_album);

        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                getPhoto();
            }
        });


        //////////////////////////////////// 취소 버튼
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });


    }

    ///////////////////////////////////on activityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        ///////////////////////////////////////////앨범에서 가져오기
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView imageView = (ImageView) findViewById(R.id.face_image);
                imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ////////////////////////////////////////////카메라로 찍어서 가져오기
        else if(requestCode==2){
            try {
                switch (requestCode) {
                    case 2: {
                        if (resultCode == RESULT_OK) {
                            File file = new File(mCurrentPhotoPath);
                            ImageView imageView = (ImageView) findViewById(R.id.face_image);
                            Bitmap bitmap = MediaStore.Images.Media
                                    .getBitmap(getContentResolver(), Uri.fromFile(file));
                            if (bitmap != null) {
                                ////////////////////////////사진 돌아가는거 되돌리기
                                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                        ExifInterface.ORIENTATION_UNDEFINED);

                                Bitmap rotatedBitmap = null;
                                switch(orientation) {
                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        rotatedBitmap = rotateImage(bitmap, 90);
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        rotatedBitmap = rotateImage(bitmap, 180);
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        rotatedBitmap = rotateImage(bitmap, 270);
                                        break;
                                    case ExifInterface.ORIENTATION_NORMAL:
                                    default:
                                        rotatedBitmap = bitmap;
                                }
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                        break;
                    }
                }

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    //////////////사진 이미지를 파일로 만들기
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    ///////사진 회전하는것 돌려주기
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    ////////사진 경로 가져오기
    public String getPath(Uri uri){

        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }

}
