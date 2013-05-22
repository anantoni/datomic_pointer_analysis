/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author
 * anantoni
 */
// Copyright (c) Metadata Partners, LLC.
// All rights reserved.

/* 
 * To compile, from the top-level project directory
 *
 *    javac -cp `bin/classpath` samples/seattle/GettingStarted.java
 *
 * To run:
 *
 *    java -cp `bin/classpath`:samples/seattle GettingStarted
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import java.io.Reader;
import java.io.FileReader;

import datomic.Entity;
import datomic.Connection;
import datomic.Database;
import datomic.Peer;
import datomic.Util;
import datomic.sql$insert;
import inputfactsconverter.InputFactsConverter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MyPointerAnalysis {

    public static void main(String[] args) {

	try {
            System.out.println( Thread.currentThread().getContextClassLoader().getResources("data_readers.clj") );

            InputFactsConverter inputFactsConverter = new InputFactsConverter();
            
	    System.out.println("Creating and connecting to database...");

	    String uri = "datomic:mem://myAnalysis";
	    Peer.createDatabase(uri);
	    Connection conn = Peer.connect(uri);



	    System.out.println("Parsing schema dtm file and running transaction...");

	    Reader schema_rdr = new FileReader("../schema_and_facts/schema.dtm");
	    List schema_tx = (List) Util.readAll(schema_rdr).get(0);
	    Object txResult = conn.transact(schema_tx).get();
	    System.out.println(txResult);

            
            insertInputFacts(conn);
            
            Collection results = Peer.q("[:find ?h :where [?h :HeapAllocationRef/heap_allocation]]", conn.db());
	    System.out.println(results.size());
            Long id = (Long) ((List)results.iterator().next()).get(0);
	    Entity entity = conn.db().entity(id);
	    System.out.println(entity.keySet());
            
            Database db = conn.db();
	    for (Object result : results) {
		entity = db.entity(((List) result).get(0));
		System.out.println(entity.get(":HeapAllocationRef/heap_allocation"));
	    }


            
	}
	catch (IOException | InterruptedException | ExecutionException e) {
	    e.printStackTrace();
            System.exit(-1);
	}
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    private static void pause() {
        if (System.getProperty("NOPAUSE") == null) {
	        System.out.println("\nPress enter to continue...");
	        scanner.nextLine();
        }
    }
    
    public static void insertInputFacts( Connection conn ) throws FileNotFoundException, InterruptedException, ExecutionException {
        System.out.println("Parsing seed data dtm file and running transaction...");
        List data_tx;
        List new_data_tx;
        Reader data_rdr = new FileReader("../schema_and_facts/Instruction-Index.dtm");
        data_tx = (List) Util.readAll(data_rdr).get(0);
	Object txResult = conn.transact(data_tx).get();
        
        data_rdr = new FileReader("../schema_and_facts/HeapAllocation-Type.dtm");
        new_data_tx = (List) Util.readAll(data_rdr).get(0);
	txResult = conn.transact(new_data_tx).get();
        

        data_rdr = new FileReader("../schema_and_facts/Test.dtm");
        new_data_tx = (List) Util.readAll(data_rdr).get(0);
	txResult = conn.transact(new_data_tx).get();
    }
}
