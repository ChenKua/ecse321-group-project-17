package ca.mcgill.ecse321.cooperator.dto;

import java.io.File;
import java.sql.Date;

import ca.mcgill.ecse321.cooperator.model.ReportStatus;
import ca.mcgill.ecse321.cooperator.model.ReportType;

public class ReportDto {
	private Integer id;
	private Date dueDate;
	private ReportStatus status;
	private ReportType type;
	private File file;

	public ReportDto() {

	}

	@SuppressWarnings("unchecked")
	public ReportDto(Integer id) {
		this(id, null, null, null, null);
	}

	public ReportDto(Integer id, Date date, ReportStatus status, ReportType type, File file) {
		this.id = id;
		this.dueDate = date;
		this.status = status;
		this.type = type;
		this.file = file;
	}

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}
	
	public Date getdueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public ReportStatus getReportStatus() {
		return status;
	}

	public void setReportStatus(ReportStatus status) {
		this.status = status;
	}

	public ReportType getReportType() {
		return type;
	}

	public void setReportType(ReportType type) {
		this.type = type;
	}
	
	public File getFile() {
		return file;
	}

}
