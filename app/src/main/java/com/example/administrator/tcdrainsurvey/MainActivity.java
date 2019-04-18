package com.example.administrator.tcdrainsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.tcdrainsurvey.base.MyApplication;
import com.example.administrator.tcdrainsurvey.bean.DrainData;
import com.example.administrator.tcdrainsurvey.database.SQLiteUtils;
import com.example.administrator.tcdrainsurvey.utils.ExcelUtils;
import com.example.administrator.tcdrainsurvey.utils.FileUtils;
import com.example.administrator.tcdrainsurvey.utils.InitFilePath;
import com.example.administrator.tcdrainsurvey.utils.PermissionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView m_tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.initPermission(this, new PermissionUtils.PermissionHolder());
        //初始化文件夹
        InitFilePath.initFolders();
        Button _btnAdd = findViewById(R.id.btnAdd);
        m_tvCount = findViewById(R.id.tv_count);

        int  count = SQLiteUtils.getInstance().queryAllContact().size();
        m_tvCount.setText("采集数量:" + count );

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrainSurveyDialogFragment _fragment = new DrainSurveyDialogFragment();
                _fragment.show(getSupportFragmentManager().beginTransaction(), "fragment");
            }
        });

        Button _btnQuery = findViewById(R.id.btnQuery);
        _btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  count = SQLiteUtils.getInstance().queryAllContact().size();

                m_tvCount.setText("采集数量:" + count );
            }
        });

        Button btnExport = findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] excelTitle = getResources().getStringArray(R.array.excel_title);
                List<DrainData> _drainDataList = SQLiteUtils.getInstance().queryAllContact();

                List<List<Object>> _lists = new ArrayList<>();
                List<Object> _list = null;
                for (DrainData _data : _drainDataList) {
                    _list = new ArrayList<>();
                    _list.add(String.valueOf(_data.getId()));
                    _list.add(String.valueOf(_data.getWaterName()));
                    _list.add(String.valueOf(_data.getRoadName()));
                    _list.add(String.valueOf(_data.getDate()));
                    _list.add(String.valueOf(_data.getWeather()));
                    _list.add(String.valueOf(_data.getUnit()));
                    _list.add(String.valueOf(_data.getSurverMan()));
                    _list.add(String.valueOf(_data.getWaterNum()));
                    _list.add(String.valueOf(_data.getTime()));
                    _list.add(String.valueOf(_data.getWaterType()));
                    _list.add(String.valueOf(String.valueOf(_data.getX())));
                    _list.add(String.valueOf(String.valueOf(_data.getY())));
                    _list.add(String.valueOf(_data.getDsType()));
                    _list.add(String.valueOf(String.valueOf(_data.getDsSize())));
                    _list.add(String.valueOf(_data.getTexture()));
                    _list.add(String.valueOf(_data.getEndControl()));
                    _list.add(String.valueOf(_data.getFlowType()));
                    _list.add(String.valueOf(String.valueOf(_data.getH())));
                    _list.add(String.valueOf(_data.getWaterLevel()));
                    _list.add(String.valueOf(_data.getDryDis()));
                    _list.add(String.valueOf(_data.getDryQuality()));
                    _list.add(String.valueOf(_data.getRainyDis()));
                    _list.add(String.valueOf(_data.getRainyQuality()));
                    _list.add(String.valueOf(_data.getPicName()));
                    _list.add(String.valueOf(_data.getRemark()));
                    _lists.add(_list);

                }

                //判断文件夹里有多少个excel文件
                int m_fileCount = 1;
                if (FileUtils.getInstance().isDirExsit(InitFilePath.PRJ_PATH)) {
                    File _file = new File(InitFilePath.PRJ_PATH);
                    File[] _files = _file.listFiles();

                    for (int _i = 0; _i < _files.length; _i++) {
                        if (_files[_i].isFile()) {
                            m_fileCount++;
                        }
                    }

                }

                String fileName = InitFilePath.PRJ_PATH + "/排水口调查-" + String.valueOf(m_fileCount) + ".xls";
                //初始化excel表
                ExcelUtils.initExcel(fileName, excelTitle, "排水口调查");
               //写入数据到excel
                ExcelUtils.writeObjListToExcel(0, _lists, fileName, MainActivity.this);

            }
        });

    }
}
