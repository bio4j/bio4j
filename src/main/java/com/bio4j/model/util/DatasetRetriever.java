package com.bio4j.model.util;

import com.bio4j.model.nodes.Dataset;

public interface DatasetRetriever extends NodeRetriever<Dataset>{

  public Dataset getDatasetByName(String name);
  public Dataset getSwissProtDataset();
  public Dataset getTremblDataset();

}
