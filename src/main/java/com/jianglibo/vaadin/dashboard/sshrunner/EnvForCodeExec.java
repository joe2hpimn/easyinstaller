package com.jianglibo.vaadin.dashboard.sshrunner;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.jianglibo.vaadin.dashboard.domain.Box;
import com.jianglibo.vaadin.dashboard.domain.BoxGroup;
import com.jianglibo.vaadin.dashboard.domain.Software;
import com.jianglibo.vaadin.dashboard.taskrunner.OneThreadTaskDesc;

/**
 * This Object will be encoded to user preferred format and upload to target
 * server as a file. can be used in user's code.
 * 
 * @author jianglibo@gmail.com
 *
 */
public class EnvForCodeExec {
	
	private final String remoteFolder;
	private final BoxDescription box;
	private final BoxGroupDescription boxGroup;
	private final SoftwareDescription software;
	
	public EnvForCodeExec(OneThreadTaskDesc oneThreadTaskDesc, String remoteFolder) {
		this.remoteFolder = remoteFolder;
		this.box = new BoxDescription(oneThreadTaskDesc.getBox());
		this.boxGroup = new BoxGroupDescription(oneThreadTaskDesc.getTd().getBoxGroup());
		this.software = new SoftwareDescription(oneThreadTaskDesc.getSoftware());
	}

	public String getRemoteFolder() {
		return remoteFolder;
	}

	public BoxDescription getBox() {
		return box;
	}

	public BoxGroupDescription getBoxGroup() {
		return boxGroup;
	}

	public SoftwareDescription getSoftware() {
		return software;
	}

	public static class BoxDescription {
		private String ip;
		private String name;
		private String hostname;
		private String roles;
		private String dnsServer;
		private String ips;
		private String ports;

		public BoxDescription(Box box) {
			this.ip = box.getIp();
			this.name = box.getName();
			this.hostname = box.getHostname();
			this.roles = box.getRoles();
			this.dnsServer = box.getDnsServer();
			this.ips= box.getIps();
			this.ports= box.getPorts();
		}
		
		public String getRoles() {
			return roles;
		}


		public void setRoles(String roles) {
			this.roles = roles;
		}


		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		public String getDnsServer() {
			return dnsServer;
		}

		public void setDnsServer(String dnsServer) {
			this.dnsServer = dnsServer;
		}

		public String getIps() {
			return ips;
		}

		public void setIps(String ips) {
			this.ips = ips;
		}

		public String getPorts() {
			return ports;
		}

		public void setPorts(String ports) {
			this.ports = ports;
		}
	}
	
	public static class SoftwareDescription {
		private String name;
		private String sversion;
		private String ostype;
		
		private Set<String> filesToUpload;
		private String configContent;
		private String actions;
		private String preferredFormat;
		
		public SoftwareDescription(Software software) {
			this.name = software.getName();
			this.sversion = software.getSversion();
			this.ostype = software.getOstype();
			this.filesToUpload = software.getFilesToUpload();
			this.configContent = software.getConfigContent();
			this.actions = software.getActions();
			this.preferredFormat = software.getPreferredFormat();
		}

		public Set<String> getFilesToUpload() {
			return filesToUpload;
		}

		public void setFilesToUpload(Set<String> filesToUpload) {
			this.filesToUpload = filesToUpload;
		}

		public String getConfigContent() {
			return configContent;
		}

		public void setConfigContent(String configContent) {
			this.configContent = configContent;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSversion() {
			return sversion;
		}

		public void setSversion(String sversion) {
			this.sversion = sversion;
		}

		public String getOstype() {
			return ostype;
		}

		public void setOstype(String ostype) {
			this.ostype = ostype;
		}

		public String getActions() {
			return actions;
		}

		public void setActions(String actions) {
			this.actions = actions;
		}

		public String getPreferredFormat() {
			return preferredFormat;
		}

		public void setPreferredFormat(String preferredFormat) {
			this.preferredFormat = preferredFormat;
		}
	}

	public static class BoxGroupDescription {

		private String name;
		private String configContent;

		private Set<BoxDescription> boxes;

		public BoxGroupDescription(BoxGroup bg) {
			this.name = bg.getName();
			this.configContent = bg.getConfigContent();

			this.setBoxes(bg.getBoxes().stream().map(b -> {
				BoxDescription bd = new BoxDescription(b);
				if (Strings.isNullOrEmpty(b.getDnsServer())) {
					bd.setDnsServer(bg.getDnsServer());
				}
				return bd;
			}).collect(Collectors.toSet()));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getConfigContent() {
			return configContent;
		}

		public void setConfigContent(String configContent) {
			this.configContent = configContent;
		}

		public Set<BoxDescription> getBoxes() {
			return boxes;
		}

		public void setBoxes(Set<BoxDescription> boxes) {
			this.boxes = boxes;
		}
	}
}