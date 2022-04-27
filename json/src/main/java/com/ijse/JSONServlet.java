package com.ijse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Step 1 - As in Response Headers the content type will be not mentioned
         * browser will have no idea of what type the response is
         * So to say the content type to the browser we should set content type when
         * sending the response from the browser.
         */

        resp.setContentType("application/json");

        /**
         * id:C00-001,
         * name:ramal,
         * address:Galle,
         * salary:5000
         */

        // -- Step 2 - Define key:value pairs to the json object that is gonna be built

        //
        // -- How to generate only a single JSON object using JSON Processing Spec----
        //

        // JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // objectBuilder.add("id", "C00-001");
        // objectBuilder.add("name", "Ramal");
        // objectBuilder.add("address", "Galle");
        // objectBuilder.add("salary", 5000);

        // -- Step 3 - Build the json object
        // JsonObject build = objectBuilder.build();

        // -- Step 4 - Send the json object as a response
        // PrintWriter writer = resp.getWriter();
        // writer.print(build);
        // writer.write(build); // can write only strings

        //
        //
        // ------- How to generate a JSON Object Array using JSON Processing Spec ---
        //
        //

        // -- Step 2
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        // -- Step 3 - Define key:value pairs to the json objects that are to be built
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", "C00-001");
        objectBuilder.add("name", "Ramal");
        objectBuilder.add("address", "Galle");
        objectBuilder.add("salary", 5000);

        JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
        objectBuilder2.add("id", "C00-002");
        objectBuilder2.add("name", "Kamal");
        objectBuilder2.add("address", "Matara");
        objectBuilder2.add("salary", 8000);

        // -- Step 4 - add the built json objects to json array
        arrayBuilder.add(objectBuilder.build());
        arrayBuilder.add(objectBuilder2.build());

        // Step - 5 - Send the json object as a response
        PrintWriter writer = resp.getWriter();
        writer.print(arrayBuilder.build());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT method invoked.....");

        /**
         * json values send with the request cannot be catched using
         * req.getParameter()
         */
        // String id = req.getParameter("id");
        // System.out.println(id);

        // ServletInputStream inputStream = req.getInputStream();
        // int read = inputStream.read();
        // System.out.println((char) read);

        // int read1 = inputStream.read();
        // System.out.println((char) read1);

        // int read2 = inputStream.read();
        // System.out.println((char) read2);

        // int read3 = inputStream.read();
        // System.out.println((char) read3);

        // PrintWriter writer = resp.getWriter();

        /**
         * inputStream.read() can read following formats
         * - json
         * - x-www-form-urlencoded (can also read this format data)
         */
        // int read;
        // while ((read = inputStream.read()) != -1) {
        // System.out.print((char) read); // prints in json format
        // }

        // ----- How to work with JSON Processing------------------
        // --- How retrieve data from JSON requests using JSON Processing Spec

        JsonReader reader = Json.createReader(req.getReader()); // json type reader
        JsonObject jsonObj = reader.readObject(); // to read a json object
        String id = jsonObj.getString("id"); // returns the value when the key is given
        String name = jsonObj.getString("name"); // returns the value when the key is given
        String address = jsonObj.getString("address"); // returns the value when the key is given
        System.out.println(id + " " + name + " " + address);

    }

}
