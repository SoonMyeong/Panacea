package com.panacea.part.model.vo;

public class MedicalPart {
	
	private String partId;
	private String partName;
	private String partIntroduce;
	
	public MedicalPart() {}

	public MedicalPart(String partId, String partName, String partIntroduce) {
		this.partId = partId;
		this.partName = partName;
		this.partIntroduce = partIntroduce;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartIntroduce() {
		return partIntroduce;
	}

	public void setPartIntroduce(String partIntroduce) {
		this.partIntroduce = partIntroduce;
	}

	@Override
	public String toString() {
		return "MedicalPart [partId=" + partId + ", partName=" + partName + ", partIntroduce=" + partIntroduce + "]";
	}

}
