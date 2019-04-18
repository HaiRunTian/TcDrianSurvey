package com.example.administrator.tcdrainsurvey;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tcdrainsurvey.bean.DrainData;
import com.example.administrator.tcdrainsurvey.database.SQLiteUtils;
import com.example.administrator.tcdrainsurvey.utils.CameraUtils;
import com.example.administrator.tcdrainsurvey.utils.DateTimeUtil;
import com.example.administrator.tcdrainsurvey.utils.FileUtils;
import com.example.administrator.tcdrainsurvey.utils.InitFilePath;
import com.example.administrator.tcdrainsurvey.utils.InitWindowSize;
import com.example.administrator.tcdrainsurvey.utils.MyAlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @author HaiRun
 * @time 2019/4/17.16:37
 */

public class DrainSurveyDialogFragment extends DialogFragment implements View.OnClickListener, IDrainSurvey, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private View m_view;
    private EditText m_edtWaterName;
    private EditText m_edtRoadName;
    private EditText m_edtDate;
    private EditText m_edtWeather;
    private EditText m_edtUnit;
    private EditText m_edtSurveyMan;
    private Spinner m_spWaterNum;
    private EditText m_edtTime;
    private Spinner m_spWaterType;
    private EditText m_edtX;
    private EditText m_edtY;
    private Spinner m_spDsType;
    private EditText m_edtDsSize;
    private EditText m_edtTexture;
    private Spinner m_spEndControl;
    private Spinner m_spFlowType;
    private EditText m_edtH;
    private Spinner m_spaterLevel;
    private EditText m_edtDryDis;
    private EditText m_edtDryQuality;
    private EditText m_edtRainyDis;
    private EditText m_edtRainyQuality;
    private GridView m_gridView;
    private Button m_btnAddPic;
    private Button m_btnClose;
    private EditText m_edtRemark;
    private Button m_btnSave;
    private File resultImgFile;
    private String pictureName = "";
    private List<File> m_fileList;
    private List<String> m_PicNameList;
    private List<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;
    private File m_pictureName;
    private final int CAME_REQUEST_CODE = 1;
    private EditText m_edtPicName;

    @Override
    public void onStart() {
        super.onStart();
        InitWindowSize.ins().initWindowSize(getActivity(), getDialog());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
        initValue();
    }

    private void initValue() {
        DrainData _data = SQLiteUtils.getInstance().queryLastContact();
        m_edtTime.setText(DateTimeUtil.setCurrentTime(DateTimeUtil.FULL_DATE_TIME_FORMAT));
        m_edtDate.setText(DateTimeUtil.setCurrentTime(DateTimeUtil.FULL_DATE_FORMAT));
        if (_data != null) {
            m_edtWaterName.setText(_data.getWaterName());
            m_edtRoadName.setText(_data.getRoadName());
            m_edtWeather.setText(_data.getWeather());
            m_edtUnit.setText(_data.getUnit());
            m_edtSurveyMan.setText(_data.getSurverMan());
            m_edtDsSize.setText(String.valueOf(_data.getDsSize()));
            m_edtTexture.setText(_data.getTexture());
        }

    }

    private void initEvent() {
        m_btnClose.setOnClickListener(this);
        m_btnAddPic.setOnClickListener(this);
        m_btnSave.setOnClickListener(this);
        m_gridView.setOnItemLongClickListener(this);
        m_gridView.setOnItemClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        m_view = inflater.inflate(R.layout.dialog_fragment_survey, container, false);
        initView(m_view);
        return m_view;
    }

    private void initView(View view) {
        m_edtWaterName = view.findViewById(R.id.edtWaterName);
        m_edtRoadName = view.findViewById(R.id.edtLocalName);
        m_edtDate = view.findViewById(R.id.edtDate);
        m_edtWeather = view.findViewById(R.id.edtWeather);
        m_edtUnit = view.findViewById(R.id.edtUnit);
        m_edtSurveyMan = view.findViewById(R.id.edtSurveyMan);
        m_spWaterNum = view.findViewById(R.id.sp_water_num);
        m_edtTime = view.findViewById(R.id.edtTime);
        m_spWaterType = view.findViewById(R.id.sp_water_type);
        m_edtX = view.findViewById(R.id.edtX);
        m_edtY = view.findViewById(R.id.edtY);
        m_spDsType = view.findViewById(R.id.sp_Ds_Type);
        m_edtDsSize = view.findViewById(R.id.edtDsSize);
        m_edtTexture = view.findViewById(R.id.edtTexture);
        m_spEndControl = view.findViewById(R.id.sp_end_control);
        m_spFlowType = view.findViewById(R.id.sp_flow_type);
        m_edtH = view.findViewById(R.id.edtH);
        m_spaterLevel = view.findViewById(R.id.sp_water_level);
        m_edtDryDis = view.findViewById(R.id.edtDryDis);
        m_edtDryQuality = view.findViewById(R.id.edtDryQuality);
        m_edtRainyDis = view.findViewById(R.id.edtRainyDis);
        m_edtRainyQuality = view.findViewById(R.id.edtRainyQuality);
        m_gridView = view.findViewById(R.id.gridView1);
        m_btnAddPic = view.findViewById(R.id.btnAddPic);
        m_btnClose = view.findViewById(R.id.btnClose);
        m_edtRemark = view.findViewById(R.id.edtRemark);
        m_btnSave = view.findViewById(R.id.btnSave);
        m_edtPicName = view.findViewById(R.id.edtPicName);


        initTakePicArea();

    }

    private void initTakePicArea() {
        m_fileList = new ArrayList<>();
        m_PicNameList = new ArrayList<>();
        imageItem = new ArrayList<HashMap<String, Object>>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
                getDialog().dismiss();
                break;

            case R.id.btnAddPic:
                openCamera();
                break;

            case R.id.btnSave:
                insertData();
                break;

            default:
                break;
        }
    }

    private void insertData() {
        try {
            DrainData _data = new DrainData();
            _data.setWaterName(getWaterName());
            _data.setRoadName(getRoadName());
            _data.setDate(getDate());
            _data.setWeather(getWeather());
            _data.setUnit(getUnit());
            _data.setSurverMan(getSurveyMan());
            _data.setWaterNum(getWaterNum());
            _data.setWaterType(getWaterType());
            _data.setTime(getTime());
            _data.setX(getX());
            _data.setY(getY());
            _data.setDsSize(getDsSize());
            _data.setDsType(getDsType());
            _data.setTexture(getTexture());
            _data.setEndControl(getEndControl());
            _data.setFlowType(getFlowType());
            _data.setH(getH());
            _data.setWaterLevel(getWaterLevel());
            _data.setDryDis(getDryDis());
            _data.setDryQuality(getDryQuality());
            _data.setRainyDis(getDrainDis());
            _data.setRainyQuality(getDrainQuality());
            _data.setPicName(getPicName());
            _data.setRemark(getRemark());
            SQLiteUtils.getInstance().addContacts(_data);
            Toast.makeText(getActivity(), "数据保存成功", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        } catch (Exception e) {
            Log.e("TAG", e.getMessage() + "error");
            Toast.makeText(getActivity(), "数据保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 手机拍照
     */
    private void openCamera() {
        InitFilePath.initFolders();
        if (m_edtPicName.getText().toString().length() > 0) {
            m_pictureName = new File(InitFilePath.PRJ_PATH_PIC, m_edtPicName.getText().toString().trim() + ".jpg");
            Uri fileUri = FileProvider.getUriForFile(getContext(), "com.example.administrator.tcdrainsurvey", m_pictureName);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAME_REQUEST_CODE);
        } else {
            Toast.makeText(getActivity(), "请先编写照片编号再拍照", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAME_REQUEST_CODE) {
            Bitmap picBitmap = null;
            picBitmap = BitmapFactory.decodeFile(m_pictureName.getAbsolutePath());
            if (picBitmap != null) {
                picBitmap = CameraUtils.comp(picBitmap);
                pictureName = m_pictureName.getName();
                m_PicNameList.add(pictureName);
                m_fileList.add(m_pictureName);
                HashMap<String, Object> _map = new HashMap<>();
                _map.put("itemImage", picBitmap);
                _map.put("picName", pictureName);
                imageItem.add(_map);
                refreshGridviewAdapter();

            } else {
                Toast.makeText(getActivity(), "照片为空", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 刷新图片区域gridview
     */
    private void refreshGridviewAdapter() {
        simpleAdapter = new SimpleAdapter(getActivity(), imageItem,
                R.layout.layout_griditem_addpic, new String[]{"itemImage", "picName"}, new int[]{R.id.imageView1, R.id.tvPicName});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(final View view, final Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {//绑定视图
                            ImageView i = (ImageView) view;
                            i.setImageBitmap((Bitmap) data);
                        }
                    });
                    return true;
                } else if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    tv.setText(textRepresentation);
                }
                return false;
            }
        });
        //主线程绑定adapter刷新数据
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                m_gridView.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取水体名称
     *
     * @return
     */
    @Override
    public String getWaterName() {
        return m_edtWaterName.getText().toString();
    }

    /**
     * 获取地段 、 河段名称
     *
     * @return
     */
    @Override
    public String getRoadName() {
        return m_edtRoadName.getText().toString();
    }

    /**
     * 获取调查日期
     *
     * @return
     */
    @Override
    public String getDate() {
        return m_edtDate.getText().toString();
    }

    /**
     * 获取天气情况
     *
     * @return
     */
    @Override
    public String getWeather() {
        return m_edtWeather.getText().toString();
    }

    /**
     * 获取检查单位
     *
     * @return
     */
    @Override
    public String getUnit() {
        return m_edtUnit.getText().toString();
    }

    /**
     * 获取调查人员
     *
     * @return
     */
    @Override
    public String getSurveyMan() {
        return m_edtSurveyMan.getText().toString();
    }

    /**
     * 获取排水口编号
     *
     * @return
     */
    @Override
    public String getWaterNum() {
        return m_spWaterNum.getSelectedItem().toString();
    }

    /**
     * 获取调查时间
     *
     * @return
     */
    @Override
    public String getTime() {
        return m_edtTime.getText().toString() + "";
    }

    /**
     * 获取WaterType
     *
     * @return
     */
    @Override
    public String getWaterType() {
        return m_spWaterType.getSelectedItem().toString();
    }

    /**
     * 获取坐标X
     *
     * @return
     */
    @Override
    public double getX() {
        String _s = m_edtX.getText().toString();
        if (_s.length() > 0) {
            return Double.valueOf(_s);
        }
        return 0.0;
    }

    /**
     * 获取坐标Y
     *
     * @return
     */
    @Override
    public double getY() {
        String _s = m_edtY.getText().toString();
        if (_s.length() > 0) {
            return Double.valueOf(_s);
        }
        return 0.0;
    }

    /**
     * 获取断面形式
     *
     * @return
     */
    @Override
    public String getDsType() {
        return m_spDsType.getSelectedItem().toString();
    }

    /**
     * 获取断面尺寸
     *
     * @return
     */
    @Override
    public double getDsSize() {
        String _s = m_edtDsSize.getText().toString();
        if (_s.length() > 0) {
            return Double.valueOf(_s);
        }
        return 0.0;
    }

    /**
     * 获取材质
     *
     * @return
     */
    @Override
    public String getTexture() {
        return m_edtTexture.getText().toString() + "";
    }

    /**
     * 末端控制
     *
     * @return
     */
    @Override
    public String getEndControl() {
        return m_spEndControl.getSelectedItem().toString();
    }

    /**
     * 出流形式
     *
     * @return
     */
    @Override
    public String getFlowType() {
        return m_spFlowType.getSelectedItem().toString();
    }

    /**
     * 官底高程
     *
     * @return
     */
    @Override
    public double getH() {
        if (m_edtH.getText().toString().length() > 0) {
            return Double.valueOf(m_edtH.getText().toString());
        }
        return 0.0;
    }

    /**
     * 水体常水位
     *
     * @return
     */
    @Override
    public String getWaterLevel() {
        return m_spaterLevel.getSelectedItem().toString();
    }

    /**
     * 旱天排水量
     *
     * @return
     */
    @Override
    public String getDryDis() {
        return m_edtDryDis.getText().toString() + "";
    }

    /**
     * 旱天排水水质
     *
     * @return
     */
    @Override
    public String getDryQuality() {
        return m_edtDryQuality.getText().toString() + "";
    }

    /**
     * 雨天排水量
     *
     * @return
     */
    @Override
    public String getDrainDis() {
        return m_edtRainyDis.getText().toString() + "";
    }

    /**
     * 雨天排水水质
     *
     * @return
     */
    @Override
    public String getDrainQuality() {
        return m_edtRainyQuality.getText().toString() + "";
    }

    /**
     * 照片名字
     *
     * @return
     */
    @Override
    public String getPicName() {
        String jointPictureName = "";
        if (m_fileList.size() == 0) {
            jointPictureName = "";
        } else if (m_fileList.size() == 1) {
            jointPictureName = m_fileList.get(0).getName();
        } else {
            for (File _picFile : m_fileList) {
                jointPictureName += _picFile.getName() + "#";
            }
            jointPictureName = jointPictureName.substring(0, jointPictureName.length() - 1);
        }
        return jointPictureName;
    }

    /**
     * 备注
     *
     * @return
     */
    @Override
    public String getRemark() {
        return m_edtRemark.getText().toString() + "";
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        MyAlertDialog.showAlertDialog(getActivity(), "删除提示", "确定删除改照片？", "确定", "取消", true,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //从手机卡删除
                        FileUtils.getInstance().deleteFile(m_fileList.get(position));
                        imageItem.remove(position);
                        m_PicNameList.remove(position);
                        m_fileList.remove(position);
                        refreshGridviewAdapter();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        viewPicture(position);
    }

    /**
     * 查看图片
     */
    private void viewPicture(int position) {
        if (m_fileList.get(position) != null) {
            //打开照片查看
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(m_fileList.get(position)), CameraUtils.IMAGE_UNSPECIFIED);
            startActivity(intent);
        }
    }
}
