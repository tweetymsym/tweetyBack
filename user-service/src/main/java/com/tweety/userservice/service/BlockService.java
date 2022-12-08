package com.tweety.userservice.service;

import com.tweety.userservice.dto.BlockRequestDto;
import com.tweety.userservice.dto.UnBlockRequestDto;
import com.tweety.userservice.exception.UserBlockingHimselfException;
import com.tweety.userservice.exception.UserNotBlockedException;
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

    public void blockUser(BlockRequestDto blockRequest)
    {
        String currentUserID= blockRequest.getCurrentUserId();
        String userToBlockID = blockRequest.getUserToBlockId();
        if(currentUserID.equals(userToBlockID)) throw new UserBlockingHimselfException(userToBlockID);

        blockRepository.blockUser(currentUserID, userToBlockID);
    }

    public void UnblockUser(UnBlockRequestDto unBlockRequest){

        String currentUserID = unBlockRequest.getCurrentUserId();
        String userTOUnBlockID = unBlockRequest.getUserToUnBlockId();
        boolean blocked = blockRepository.UnBlockUser(currentUserID, userTOUnBlockID);

        if (blocked) throw new UserNotBlockedException(currentUserID, userTOUnBlockID);
    }

    public List<User> getBlockedUsers(String UserID) {

        return blockRepository.getBlocks(UserID);
    }

}
