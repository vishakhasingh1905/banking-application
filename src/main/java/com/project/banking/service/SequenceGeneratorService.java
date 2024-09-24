package com.project.banking.service;

import com.project.banking.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        // Query to find the sequence by its name (e.g., "account-sequence")
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        // Fetch or create the sequence document
        Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class, "sequences");

        // If the sequence is being created for the first time, initialize it at 10000001
        if (sequence == null) {
            // Initialize the sequence with a starting value of 10000001
            sequence = new Sequence(seqName, 10000000); // Set to 10000000 so the first increment makes it 10000001
            mongoOperations.save(sequence, "sequences");
        }

        // Return the next value in the sequence (will be 10000001 for the first account)
        return sequence.getSeq();
    }

}
