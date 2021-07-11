package com.kankan.discover.model.schoolv2;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class SchoolV2Info {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SchoolEQAOInfo {
    @Id
    private String id;
    private String schoolID;
    private String yearMark;
    private String g3STU;
    private String g3AR;
    private String g3AW;
    private String g3AM;
    private String g3Rank;
    private String g3Total;
    private String g3FindSchoolRating;
    private String g6STU;
    private String g6AR;
    private String g6AW;
    private String g6AM;
    private String g6Rank;
    private String g6Total;
    private String g6FindSchoolRating;
    private String g9ACSTU;
    private String g9ACM;
    private String g9ACRank;
    private String g9ACTotal;
    private String g9APSTU;
    private String g9APM;
    private String g9APRank;
    private String g9APTotal;
    private String g9Rank;
    private String g9Total;
    private String g9FindSchoolRating;
    private String g10OSSLTFSTUA;
    private String g10OSSLTFSTUP;
    private String g10OSSLTFSUCCESS;
  }

  @Id
  private String schoolID;
  private String alias;
  private String uRL;
  private Integer type;
  private Integer gradeFrom;
  private Integer gradeEnd;
  private Integer isElementary;
  private Integer isIsMiddle;
  private Integer isHigh;
  private Integer isFrenchImmersion;
  private Integer isAP;
  private Integer isIB;
  private Integer isGifted;
  private Integer isArts;
  private Integer isSport;
  private String boardName;
  private String address;
  private String city;
  private String postalCode;
  private String province;
  private String phone;
  private String fax;
  @GeoSpatialIndexed(name = "location")
  private Point location;//经纬度
  private List<SchoolEQAOInfo> eaqoInfoList;
}
