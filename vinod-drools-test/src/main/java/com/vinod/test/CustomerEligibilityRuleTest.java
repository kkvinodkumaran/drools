package com.vinod.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CustomerEligibilityRuleTest {

	public static void main(String[] args) {
		List<File> rules = new ArrayList<File>();
		File currentdir = new File(System.getProperty("user.dir"));
		File[] listFiles = currentdir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.endsWith(".drl"));
			}
		});
		for (File listFile : listFiles) {
			System.out.println(listFile.getName());
			rules.add(listFile);
		}
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("Vinod k kumaran", 21));
		executeRules(rules, customers);
	}

	public static void executeRules(List<File> files, List<Customer> customers) {
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieResources kieResources = kieServices.getResources();
			KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
			KieRepository kieRepository = kieServices.getRepository();
			for (File ruleFile : files) {
				Resource resource = kieResources.newFileSystemResource(ruleFile);
				kieFileSystem.write(resource);
			}
			KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
			kb.buildAll();
			KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
			KieSession kSession = kContainer.newKieSession();
			kSession.addEventListener(new CustomerRuleTracker());
			for (Object customer : customers) {
				kSession.insert(customer);
			}
			kSession.fireAllRules();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
