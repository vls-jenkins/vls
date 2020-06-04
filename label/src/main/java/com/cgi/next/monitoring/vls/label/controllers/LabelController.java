package com.cgi.next.monitoring.vls.label.controllers;

import com.cgi.next.monitoring.vls.core.domain.Address;
import com.cgi.next.monitoring.vls.core.domain.Label;
import com.cgi.next.monitoring.vls.label.services.LabelService;
import com.itextpdf.text.pdf.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class that handles incoming restful calls, which concern the resource {@code label}
 */
@RestController
public class LabelController {
    private final LabelService labelService;

    @Autowired
    public LabelController(final LabelService labelService){
        this.labelService = labelService;
    }


    /**
     * returns a binary pdf file containing a label
     * @param id
     * @return
     */
    @RequestMapping(value = "/labels/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLabel(@PathVariable long id){
        final Label label = labelService.getLabelById(id);
        final ByteArrayResource resource = new ByteArrayResource(Base64.decode(label.getBase64EncodedLabel()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=label.pdf")
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

    }

    @RequestMapping(value = "labels", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createLabel(@RequestBody Map<String, Object> requestBody){
        final String shipmentNumber = requestBody.get("shipmentNumber").toString();
        final Address  recipientAddress = new Address();
        recipientAddress.setLine1((String)((Map<String,Object>)requestBody.get("recipientAddress")).get("line1"));
        recipientAddress.setLine2((String)((Map<String,Object>)requestBody.get("recipientAddress")).get("line2"));
        recipientAddress.setLine3((String)((Map<String,Object>)requestBody.get("recipientAddress")).get("line3"));
        final Label label = labelService.createLabel(recipientAddress, shipmentNumber);
        return ResponseEntity.created(
                URI.create(linkTo(methodOn(LabelController.class).getLabel(label.getId())).withSelfRel().getHref()))
                .build();
    }
}
