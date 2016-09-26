package com.ydx.facepp.faceppmanager;

import android.text.TextUtils;
import android.util.Log;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.ydx.facepp.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/9/13.
 */
public class FacePPManager {
    private static HttpRequests request=new HttpRequests();
    private static FacePPManager facePPManager=new FacePPManager();
    private static IFaceppCallBack iFaceppCallBack;
    public static FacePPManager getInstance(){
        return facePPManager;
    }

    public void initRequest(){
        request.setApiSecret(Config.API_SECRET);
        request.setApiKey(Config.API_KEY);
        request.setWebSite(true,true);
    }

    public void initIFaceppCallBack(IFaceppCallBack iFaceppCallBack){
        this.iFaceppCallBack=iFaceppCallBack;
    }


    /**
     *
     * @param personName 全局唯一 暂时设定为 人名+生日
     */
    public JSONObject createPreson(String personName){
        try {
            PostParameters postParameters=new PostParameters();
            postParameters.setPersonName(personName);
            JSONObject personCreate=request.personCreate(postParameters);
            Log.i("personCreate:",personCreate.toString());
            return personCreate;
        } catch (FaceppParseException e) {
            e.printStackTrace();
           iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.personCreate);
        }
        return null;
    }

    /**
     * 为指定人 添加 faceId
     * @param personName
     * @param faceIds
     */
    public JSONObject addFaceToPerson(String personName, ArrayList<String> faceIds){
        try {
            PostParameters postParameters=new PostParameters();
            postParameters.setPersonName(personName);
            postParameters.setFaceId(faceIds);
            JSONObject addFaceToPerson= request.personAddFace(postParameters);
            Log.i("addFaceToPerson:",addFaceToPerson.toString());
            return  addFaceToPerson;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.personAddFace);
        }
        return null;
    }


    public JSONObject getPersonInfo(String personName){
        try {
            PostParameters postParameters=new PostParameters();
            postParameters.setPersonName(personName);
            JSONObject personInfo= request.personGetInfo(postParameters);
            Log.i("personInfo:",personInfo.toString());
            return  personInfo;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.personGetInfo);
        }
        return null;
    }

    //***************************************faceSet***************************************************

    /**
     * create a faceSet
     * @param faceSetName   unique
     * @param faceIds       can be null
     * @param tag           can be null
     * @return
     */
    public JSONObject createFaceSet(String faceSetName,ArrayList faceIds,String tag){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        if(faceIds!=null&&!faceIds.isEmpty())
            postParameters.setFaceId(faceIds);
        if(tag!=null&&!tag.isEmpty())
            postParameters.setTag(tag);
        try {
           JSONObject createFaceSet= request.facesetCreate(postParameters);
            Log.i("createFaceSet:",createFaceSet.toString());
            return createFaceSet;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetCreate);
        }
        return null;
    }

    /**
     * delete faceSets
     * @param faceSetIds
     * @return
     */
    public JSONObject deleteFaceSets(ArrayList<String> faceSetIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setFaceId(faceSetIds);
        try {
            JSONObject createFaceSet= request.facesetDelete(postParameters);
            Log.i("deleteFaceSet:",createFaceSet.toString());
            return createFaceSet;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetCreate);
        }
        return null;
    }



    /**
     * 向 faceSet  添加一个 faceId
     * @param faceSetName
     * @param faceIds
     */
    public JSONObject addFaceToFaceSet(String faceSetName,ArrayList<String> faceIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        postParameters.setFaceId(faceIds);
        try {
          JSONObject addFaceToFaceSet=  request.facesetAddFace(postParameters);
            Log.i("addFaceToFaceSet:",addFaceToFaceSet.toString());
            return addFaceToFaceSet;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetAddFace);
        }
        return null;
    }

    /**
     * remove faceIds from target faceSet
     * @param faceSetName
     * @param faceIds
     * @return
     */
    public JSONObject removeFaceOfFaceSet(String faceSetName,ArrayList<String> faceIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        postParameters.setFaceId(faceIds);
        try {
            JSONObject addFaceToFaceSet=  request.facesetRemoveFace(postParameters);
            Log.i("removeFaceOfFaceSet:",addFaceToFaceSet.toString());
            return addFaceToFaceSet;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetDeleteFace);
        }
        return null;
    }

    /**
     * remove all face from target faceSet
     * @param faceSetName
     * @return
     */
    public JSONObject removeAllFaceOfFaceSet(String faceSetName){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        postParameters.setFaceId("all");
        try {
            JSONObject addFaceToFaceSet=  request.facesetRemoveFace(postParameters);
            Log.i("removeAllFaceOfFaceSet:",addFaceToFaceSet.toString());
            return addFaceToFaceSet;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetDeleteFace);
        }
        return null;
    }

    /**
     * update faceSet info
     * @param faceSetName
     * @param newName
     * @param newTag
     * @return
     */
    public JSONObject setFaceSetInfo(String faceSetName,String newName,String newTag){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        if(newName!=null&&!newName.isEmpty())
            postParameters.setName(newName);
        if(!TextUtils.isEmpty(newTag))
            postParameters.setTag(newTag);
        try {
            JSONObject addFaceToFaceSet=  request.facesetSetInfo(postParameters);
            Log.i("setFaceSetInfo:",addFaceToFaceSet.toString());
            return addFaceToFaceSet;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetSetInfo);
        }
        return null;
    }

    /**
     * get faceSet info
     * @param faceSetName
     * @return
     *
     */


    public JSONObject getFaceSetInfo(String faceSetName){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        try {
            JSONObject addFaceToFaceSet=  request.facesetGetInfo(postParameters);
            Log.i("getFaceSetInfo:",addFaceToFaceSet.toString());
            return addFaceToFaceSet;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.faceSetGetInfo);
        }
        return null;
    }

    //*************************recognition***************************************************
    //return:
    //    {
    //        "component_similarity": {
    //                "eye": 73.679835,
    //                "mouth": 77.390801,
    //                "nose": 80.22716,
    //                "eyebrow": 74.043414
    //        },
    //        "session_id": "822e13ce5ae9896dbb18f0141d3fd254",
    //            "similarity": 74.905818
    //    }
    public JSONObject compare(String faceId1,String faceId2){
        PostParameters postParameters=new PostParameters();
        postParameters.setFaceId1(faceId1);
        postParameters.setFaceId2(faceId2);
        try {
          JSONObject result= request.recognitionCompare(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.recognitionCompare);
        }

        return null;
    }

    /**
     * recognition the face in the target group
     * @param groupId
     * @param file
     * @return
     */
    public JSONObject recognitionFromGroup(String groupId,File file){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupId(groupId);
        postParameters.setImg(file);
        try {
            JSONObject result= request.recognitionIdentify(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.recognitionIdentify);
            e.printStackTrace();
        }
        return null;
    }


//    return:
//    {
//        "candidate": [
//        {
//            "face_id": "a9cebf8d5ae6fff514d8d2d8e07fa55b",
//                "similarity": 100,
//                "tag": ""
//        },
//        {
//            "face_id": "7f2de0e85bede3171c839d0dcabd849f",
//                "similarity": 55.379097,
//                "tag": ""
//        },
//        {
//            "face_id": "40ebb31e8af7237a73dec9f242885a7e",
//                "similarity": 52.59766,
//                "tag": ""
//        }
//        ],
//        "session_id": "670ad0f86fb809b26421003ffba1a768"
//    }

    /**
     * give a face  search in given face set
     * @param keyFaceId         key face
     * @param faceSetName       which face set
     * @param resultCount       max of result count
     * @return
     */
    public JSONObject recognitionFromFaceSet(String keyFaceId,String faceSetName,int resultCount){
        PostParameters postParameters=new PostParameters();
        postParameters.setKeyFaceId(keyFaceId);
        postParameters.setFacesetName(faceSetName);
        postParameters.setCount(resultCount);
        try {
           JSONObject result= request.recognitionSearch(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.recognitionSearch);
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject checIsSamePerson(String faceId,String personNname){
        PostParameters postParameters=new PostParameters();
        postParameters.setFaceId(faceId);
        postParameters.setPersonName(personNname);
        try {
          return request.recognitionVerify(postParameters);
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.isSamePerson);
        }
        return  null;
    }
//*****************************************train*****************************************************

    /**
     * train a person
     * @param personName
     * @return  sessionId
     */
    public String  trainVerify(String personName){
        PostParameters postParameters=new PostParameters();
        postParameters.setPersonName(personName);
        try {
          return   request.trainVerify(postParameters).getString("session_id");
        }catch (FaceppParseException e){
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.trainVerfy);
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    /**
     * train a faceSet
     * @param faceSetName
     * @return sessionId
     */
    public String trainSearch(String faceSetName){
        PostParameters postParameters=new PostParameters();
        postParameters.setFacesetName(faceSetName);
        try {
          JSONObject result=request.trainSearch(postParameters);
            return result.getString("session_id");
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.trainSearch);
        }catch (Exception e){

        }
        return "";
    }


    /**
     * train a group
     * @param groupId
     * @return sessionId
     */
    public String trainIdentify(String groupId){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupId(groupId);
        try {
            JSONObject result=request.trainSearch(postParameters);
            return result.getString("session_id");
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.trainidentify);
        }catch (Exception e){

        }
        return "";
    }

    //*******************************************check face**********************************************

    public JSONObject checkFace(byte [] bytes){
        PostParameters postParameters=new PostParameters();
        postParameters.setImg(bytes);
        try {
            JSONObject checkResult=request.detectionDetect(postParameters);
            Log.i("checkResult:",checkResult.toString());
            return checkResult;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.checkPic);
        }
        return null;
    }



    public JSONObject checkFace(File file){
        PostParameters postParameters=new PostParameters();
        postParameters.setImg(file);
        try {
            JSONObject checkResult=request.detectionDetect(postParameters);
            Log.i("checkResult:",checkResult.toString());
            return checkResult;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.checkPic);
        }
        return null;
    }

    public String getFaceId(File file){
        PostParameters postParameters=new PostParameters();
        postParameters.setImg(file);
        try {
            JSONObject checkResult=request.detectionDetect(postParameters);
            Log.i("checkResult:",checkResult.toString());
            try {
                if(checkResult.getJSONArray("face").length()>1)
                    return "";
                String faceId=  checkResult.getJSONArray("face").getJSONObject(0).getString("face_id");
                return faceId;
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }

        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.checkPic);
        }
        return null;

    }

    /**
     * 检测一张照片 返回多个faceId
     * @param file
     * @return
     */
    public ArrayList<String> getFaceIds(File file){
        ArrayList<String> faceIds=new ArrayList<>();
        PostParameters postParameters=new PostParameters();
        postParameters.setImg(file);
        try {
            JSONObject checkResult=request.detectionDetect(postParameters);
            Log.i("checkResult:",checkResult.toString());
            try {
                JSONArray faces= checkResult.getJSONArray("face");
                if(faces!=null&&faces.length()>0){
                    for(int i=0;i<faces.length();i++){
                        String faceId= faces.getJSONObject(i).getString("face_id");
                        faceIds.add(faceId);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.checkPic);
        }
        return faceIds;

    }


    //***********************************group*****************************************************

    public JSONObject createGroup(String groupName, ArrayList<String> personIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupName(groupName);
        if(personIds!=null&&personIds.size()>0){
            postParameters.setPersonName(personIds);
        }
        try {
            JSONObject result= request.groupCreate(postParameters);
            return result;
        } catch (FaceppParseException e) {
          iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupCreate);
        }
        return null;
    }

    public JSONObject deleteGroup( ArrayList<String> groupIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupId(groupIds);
        try {
            JSONObject result= request.groupDelete(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupDelete);
        }
        return null;
    }

    public JSONObject addPersonToGroup(String groupName,ArrayList<String> personNames){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupName(groupName);
        postParameters.setPersonName(personNames);
        try {
            JSONObject result= request.groupAddPerson(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupAddPerson);
        }
        return null;
    }

    /**
     * delete given persons from group
     * @param groupName
     * @param personNames
     * @return
     */
    public JSONObject deletePersonOfGroup(String groupName,ArrayList<String> personNames){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupName(groupName);
        postParameters.setPersonName(personNames);
        try {
            JSONObject result= request.groupRemovePerson(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupRemovePerson);
        }
        return null;
    }

    /**
     * delete all person of this group
     * @param groupName
     * @return
     */
    public JSONObject deleteAllPersonOfGroup(String groupName){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupName(groupName);
        postParameters.setPersonId("all");
        try {
            JSONObject result= request.groupRemovePerson(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupRemovePerson);
        }
        return null;
    }

    /**
     * update group info
     * @param groupId   target group
     * @param newName   new groupName
     * @param newTag    new groupTag
     * @return
     */
    public JSONObject setGroupInfo(String groupId,String newName,String newTag){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupId(groupId);
        if(!TextUtils.isEmpty(newName))
            postParameters.setName(newName);
        if(!TextUtils.isEmpty(newTag))
            postParameters.setTag(newTag);
        try {
            JSONObject result= request.groupSetInfo(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupSetInfo);
        }
        return null;
    }

    /**
     * get group info which contains personList
     * @param groupName
     * @return
     */
    public JSONObject getGroupInfo(String groupName){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupName(groupName);
        try {
            JSONObject result= request.groupGetInfo(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupGetInfo);
        }
        return null;
    }

    /**
     * get all persons which do not join a group
     * @return
     */
    public JSONObject getAllNoGroupPersons(){
        PostParameters postParameters=new PostParameters();
        postParameters.setGroupId("none");
        try {
            JSONObject result= request.groupGetInfo(postParameters);
            return result;
        } catch (FaceppParseException e) {
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.groupGetInfo);
        }
        return null;
    }

    //*****************************************Info******************************
    public JSONObject getResultBySessionId(String sessionId){
        PostParameters postParameters=new PostParameters();
        postParameters.setSessionId(sessionId);
        try {
            JSONObject getResultBySessionId=request.getSessionSync(sessionId,30000);
            Log.i("getResultBySessionId:",getResultBySessionId.toString());
            return getResultBySessionId;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.infoGetSession);
        }
        return null;
    }


    public JSONObject getFaceInfo(ArrayList<String> faceIds){
        PostParameters postParameters=new PostParameters();
        postParameters.setFaceId(faceIds);
        try {
            JSONObject result=request.infoGetFace(postParameters);
            Log.i("getFaceInfo:",result.toString());
            return result;
        } catch (FaceppParseException e) {
            e.printStackTrace();
            iFaceppCallBack.onFaceppFail(e,FaceppMethodEnum.infoGetSession);
        }
        return null;
    }




}
