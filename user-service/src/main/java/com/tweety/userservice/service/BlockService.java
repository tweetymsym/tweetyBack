package com.tweety.userservice.service;

import com.tweety.userservice.dto.BlockRequest;
import com.tweety.userservice.dto.UnBlockRequest;
import com.tweety.userservice.model.User;
import com.tweety.userservice.repository.BlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BlockService {

    private BlockRepository blockRepository;

    public Boolean blockUser(BlockRequest blockRequest)
    {
        String currentUserID= blockRequest.getCurrentUserId();
        String userToBlockID = blockRequest.getUserToBlockId();
        if(currentUserID.equals(userToBlockID)) throw new  IllegalArgumentException("You can't Block Your self");

        return blockRepository.blockUser(currentUserID,userToBlockID);
    }

    public  Boolean UnblockUser(UnBlockRequest unBlockRequest){

        String currentUserID = unBlockRequest.getCurrentUserId();
        String userTOUnBlockID = unBlockRequest.getUserToUnBlockId();
        return blockRepository.UnBlockUser(currentUserID,userTOUnBlockID);
    }

    public List<User> getBlockedUsers(String UserID)
    {
        List<User> blockedUsers=blockRepository.getBlocks(UserID);

        return blockedUsers;

    }

}
