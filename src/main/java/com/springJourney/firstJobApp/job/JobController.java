package com.fargaislam.firstJobApp.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {

        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job==null){
            return new ResponseEntity<>(new Job(1l,"TestJob","Test Job",
                    "2000","1000","Pakistan"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted=jobService.deleteJobById(id);
        if(deleted){
            return ResponseEntity.ok("Job deleted successfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                             @RequestBody Job updatedJob){
        Boolean updated =jobService.updateJob(id,updatedJob);
        if(updated)
        {
            return ResponseEntity.ok("Job updated successfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
