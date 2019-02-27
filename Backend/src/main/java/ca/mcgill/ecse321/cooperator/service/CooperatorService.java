package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.ReportRepository;
import ca.mcgill.ecse321.cooperator.dao.NotificationRepository;
import ca.mcgill.ecse321.cooperator.dao.ProfileRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopStatus;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Report;
import ca.mcgill.ecse321.cooperator.model.ReportStatus;
import ca.mcgill.ecse321.cooperator.model.ReportType;
import ca.mcgill.ecse321.cooperator.model.Notification;
import ca.mcgill.ecse321.cooperator.model.Profile;
import ca.mcgill.ecse321.cooperator.model.Student;

@Service
public class CooperatorService {

	@Autowired (required = true)
	CoopRepository coopRepository;
	@Autowired (required = true)
	EmployerRepository employerRepository;
	@Autowired (required = true)
	ReportRepository reportRepository;
	@Autowired (required = true)
	StudentRepository studentRepository;
	@Autowired (required = true)
	ProfileRepository profileRepository;
	@Autowired (required = true)
	NotificationRepository notificationRepository;
	@Autowired (required = true)
	AdministratorRepository administratorRepository;

	@Transactional
	public Student createStudent(String email, String name, String password, String phone, Integer id) {
		Student p = new Student();
		String error = "";
		if(name == null || name.trim().length() == 0) {
			error = "Student name cannot be empty! ";
	    }
		if(email == null || email.trim().length() == 0) {
			error =  error + "Email cannot be empty! ";
		}
		if(password == null || password.trim().length() == 0) {
			error =  error + "Password cannot be empty! ";
		}
		if(phone == null || phone.trim().length() == 0) {
			error =  error + "Phone cannot be empty! ";
		}
		if(id < 0) {
			error =  error + "ID is invalid!";
		}
		if(error.length() != 0) {
			throw new IllegalArgumentException(error);
		}
		p.setEmail(email);
		p.setName(name);
		p.setPassword(password);
		p.setPhone(phone);
		p.setId(id);
		studentRepository.save(p);
		return p;
	}
	
	@Transactional
	public Employer createEmployer(String email, String name, String password, String phone, Integer id) {
		Employer e = new Employer();
		
		String error = "";
		if(name == null || name.trim().length() == 0) {
			error = "Employer name cannot be empty! ";
		}
		if(email == null || email.trim().length() == 0) {
			error =  error + "Email cannot be empty! ";
		}
		if(password == null || password.trim().length() == 0) {
			error =  error + "Password cannot be empty! ";
		}
		if(phone == null || phone.trim().length() == 0) {
			error =  error + "Phone cannot be empty! ";
		}
		if(id < 0) {
			error =  error + "ID is invalid!";
		}
		if(error.length() != 0) {
			throw new IllegalArgumentException(error);
		}
		e.setEmail(email);
		e.setName(name);
		e.setPassword(password);
		e.setPhone(phone);
		e.setId(id);
		employerRepository.save(e);
		return e;
	}
	
	@Transactional
	public Administrator createAdmin(String email, String name, String password, String phone, Integer id) {
		Administrator a = new Administrator();
		
		String error = "";
		if(name == null || name.trim().length() == 0) {
			error = "Administrator name cannot be empty! ";
			}
		if(email == null || email.trim().length() == 0) {
			error =  error + "Email cannot be empty! ";
			}
		if(password == null || password.trim().length() == 0) {
			error =  error + "Password cannot be empty! ";
			}
		if(phone == null || phone.trim().length() == 0) {
			error =  error + "Phone cannot be empty! ";
			}
		if(id < 0) {
			error =  error + "ID is invalid!";
		}
		if(error.length() != 0) {
			throw new IllegalArgumentException(error);
		}
		a.setEmail(email);
		a.setName(name);
		a.setPassword(password);
		a.setPhone(phone);
		a.setId(id);
		administratorRepository.save(a);
		return a;
	}

	@Transactional 
	public Coop createCoop(Student student, Employer employer, String title, Integer id, Date startDate, Date endDate, CoopStatus status, Integer salaryPerHour, Integer hoursPerWeek, String address) {
		String error = "";

		if(student == null) {
			error = error + "Student is null! ";
		}
		if(employer == null) {
			error = error + "Employer is null!";
		}
		if(error.length()!= 0) {
			throw new IllegalArgumentException(error);
		}
		error = "";
		if(id < 0) {
			error = error + "ID is invalid! ";
		}
		if(title == null || title.trim().length() == 0) {
			error = error + "Coop title cannot be empty! ";
		}
		if(startDate == null) {
			error = error + "Coop start date cannot be empty! ";
		}
		if(endDate == null) {
			error = error + "Coop end date cannot be empty! ";
		}
		if(status == null) {
			error = error + "Coop status invalid! ";
		}
		if(salaryPerHour <= 0 || salaryPerHour == null) {
			error = error + "Salary per hour is invalid! ";
		}
		if(hoursPerWeek <= 0 || hoursPerWeek == null ) {
			error = error + "Hours per week is invalid! ";
		}

		if(address == null || address.trim().length() ==  0) {
			error = error + "Address cannot be empty!";
		}

		if(error.length()!= 0) {
			throw new IllegalArgumentException(error);
		}
		error = "";
		if(startDate.after(endDate)) {
			throw new IllegalArgumentException("Coop end time cannot be before Coop start time!");
		}
		Coop c = new Coop();
		c.setId(id);
		c.setEmployer(employer);
		c.setStudent(student);
		c.setTitle(title);
		c.setStartDate(startDate);
		c.setEndDate(endDate);
		c.setStatus(status);
		c.setSalaryPerHour(salaryPerHour);
		c.setHoursPerWeek(hoursPerWeek);
		c.setAddress(address);
		coopRepository.save(c);
		return c;
	}
	
	@Transactional 
	public Coop getCoop(Integer id) {
		Coop c = coopRepository.findCoopByid(id);
		return c;
	}
	
	@Transactional
	public List<Coop> getAllCoops() {
		return toList(coopRepository.findAll());
	}
	
	@Transactional
	public Set<Coop> getCoopforStudent(Student s){
		if(s == null) {
			throw new IllegalArgumentException("Student is null!");
		}
		Set<Coop> stuCoops = coopRepository.findCoopByStudent(s);
		return stuCoops;
	}
	
	@Transactional 
	public Student getStudent(String email) {
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Student email cannot be empty!");
		}
		Student s = studentRepository.findStudentByEmail(email);
		return s;
	}
	
	@Transactional 
	public Administrator getAdmin(String email) {
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Administrator email cannot be empty!");
		}
		Administrator a = administratorRepository.findAdministratorByEmail(email);
		return a;
	}
	
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}
	
	@Transactional 
	public Employer getEmployer(String email) {
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Employer email cannot be empty!");
		}
		
		Employer e = employerRepository.findEmployerByEmail(email);
		return e;
	}
	
	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}
	
	@Transactional
	public int getNumberofProfiles() {
		int num = (int) studentRepository.count();
		num += (int) employerRepository.count();
		num += (int) administratorRepository.count();
		return num;
	}
	
	
	@Transactional
	public List<Administrator> getAllAdministrators() {
		return toList(administratorRepository.findAll());
	}
	
	@Transactional  
	public Notification createNotification(Integer id, String text, Administrator a, Student s, Employer e) {
		String error = "";
		if(a == null) {
			error = error + "Administrator is null! ";
		}
		if(s == null && e == null) {
			error = error + "Notification needs at least one recipient! ";
		}
		if(id == null || id < 0) {
			error = error + "ID is invalid! ";
		}
		if(text == null || text.trim().length() == 0) {
			error = error + "Text is invalid!";
		}
		if(error.length() != 0) {
			throw new IllegalArgumentException(error);
		}
		
		Notification n = new Notification();
		n.setId(id);
		n.setText(text);
		n.setSender(a);
		n.setEmployer(e);
		n.setStudent(s);
		
		if (a != null) {
			Set<Notification> notifs = a.getSent();
			if (notifs == null) notifs = new HashSet<>();
			notifs.add(n);
			a.setSent(notifs);
		}

		if (e != null) {
			Set<Notification> notifs = e.getReceived();
			if (notifs == null) notifs = new HashSet<>();
			notifs.add(n);
			e.setReceived(notifs);
		}
		if (s != null) {
			Set<Notification> notifs = s.getReceived();
			if (notifs == null) notifs = new HashSet<>();
			notifs.add(n);
			s.setReceived(notifs);
		}

		notificationRepository.save(n);
		return n;
	}
	
	public Set<Notification> getNotificationsEmp(Employer e) {
		Set <Notification> n = null;
		if(e == null) {
			throw new IllegalArgumentException("Profile cannot be null!");
		}
		else {
			n = e.getReceived() == null ? new HashSet<>() : e.getReceived();
		}
		return n;
	}
	
	@Transactional  
	public Set<Notification> getNotificationsStu(Student s) {
		Set <Notification> n = null;
		if(s == null) {
			throw new IllegalArgumentException("Profile cannot be null!");
		}
		else {
			n = s.getReceived() == null ? new HashSet<>() : s.getReceived();
		}
		return n;
	} 
	
	@Transactional  
	public Set<Notification> getNotificationsAdm(Administrator a) {
		Set <Notification> n = null;
		if(a == null) {
			throw new IllegalArgumentException("Profile cannot be null!");
		}
		else {
			n = a.getSent() == null ? new HashSet<>() : a.getSent();
		}
		return n;
	} 
	
	@Transactional
	public List<Notification> getAllNotifications() {
		return toList(notificationRepository.findAll());
	}
	
	@Transactional  
	public Report createReport(Integer id, Coop c, Date d, ReportStatus s, ReportType t) {
		String error = "";
		if(id == null || id < 0) {
			error = ("ID is invalid! ");
		}
		if(c == null) {
			error = error + "Coop is null! ";
		}
		if(d == null) {
			error = error + "Due date is invalid! ";
		}
		if(s == null ) {
			error = error + "Status is invalid! ";
		}
		if(t == null) {
			error = error + "Type is invalid!";
		}
		if(error.length() != 0) {
			throw new IllegalArgumentException(error);
		}
		
		Report r = new Report();
		r.setId(id);
		r.setCoop(c);
		r.setDueDate(d);
		r.setStatus(s);
		r.setType(t);
		reportRepository.save(r);
		return r;
	}
	
	@Transactional  
	public Optional<Report> getReport(Integer id) {
		Optional<Report> r = reportRepository.findById(id);
		return r;
	}
	
	@Transactional
	public List<Report> getAllReports() {
		return toList(reportRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	
	
}