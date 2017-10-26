create table SIDX_ANNUAL_PAYMENT (SAP_ID number(19,0) not null, SAP_BONUS_AMOUNT number(12,2) not null, SAP_CR_DT date not null, SAP_CR_UID varchar2(32) not null, SAP_REMARK varchar2(256), SAP_STATUS varchar2(32) not null, SAP_UPD_DT date, SAP_UPD_UID varchar2(32), SAP_SCP_ID number(19,0) not null, primary key (SAP_ID));
create table SIDX_ANSWER (SA_ID number(19,0) not null, SA_CR_DT date not null, SA_CR_UID varchar2(32) not null, SA_UPD_DT date, SA_UPD_UID varchar2(32), SA_CP_ID number(19,0), SA_SO_ID number(19,0), SA_SQ_ID number(19,0), primary key (SA_ID));
create table SIDX_ANSWER_DOCUMENT (SAD_ID number(19,0) not null, SAD_CR_DT date not null, SAD_CR_UID varchar2(32) not null, SAD_EXPIRY_DATE date, SAD_STATUS varchar2(32) not null, SAD_UPD_DT date, SAD_UPD_UID varchar2(32), SAD_SA_ID number(19,0), SAD_SDI_ID number(19,0), SAD_SRR_ID number(19,0), primary key (SAD_ID));
create table SIDX_CLIENT_NUMBER (SCN_ID number(19,0) not null, SCN_BUSINESS_UNIT varchar2(32) not null, SCN_CR_DT date not null, SCN_CR_UID varchar2(32) not null, SCN_NUMBER varchar2(32) not null, SCN_UPD_DT date, SCN_UPD_UID varchar2(32), SCN_CLIENT_PROFILE_ID number(19,0), primary key (SCN_ID), unique (SCN_NUMBER, SCN_BUSINESS_UNIT));
create table SIDX_CLIENT_PROFILE (SCP_ID number(19,0) not null, SCP_CR_DT date not null, SCP_CR_UID varchar2(32) not null, SCP_FIRSTNAMES varchar2(128) not null, SCP_IDENTITY_NUMBER varchar2(13) not null, SCP_LASTNAME varchar2(128) not null, SCP_SUBMIT_TIMESTAMP date not null, SCP_TITLE varchar2(12) not null, SCP_UPD_DT date, SCP_UPD_UID varchar2(32), primary key (SCP_ID), unique (SCP_IDENTITY_NUMBER));
create table SIDX_CLIENT_QUESTIONNAIRE (SCQ_ID number(19,0) not null, SCQ_CR_DT date not null, SCQ_CR_UID varchar2(32) not null, SCQ_STATUS varchar2(48) not null, SCQ_UPD_DT date, SCQ_UPD_UID varchar2(32), SCQ_CLIENT_PROFILE_ID number(19,0), SCQ_QUESTIONNAIRE_ID number(19,0), primary key (SCQ_ID));
create table SIDX_DOCUMENT (SD_ID number(19,0) not null, SD_CR_DT date not null, SD_CR_UID varchar2(32) not null, SD_DESCRIPTION varchar2(256), SD_TYPE varchar2(256) not null, SD_UPD_DT date, SD_UPD_UID varchar2(32), primary key (SD_ID), unique (SD_TYPE, SD_DESCRIPTION));
create table SIDX_DOCUMENT_INSTANCE (SDI_ID number(19,0) not null, SDI_CR_DT date not null, SDI_CR_UID varchar2(32) not null, SDI_FILE_UUID varchar2(64) not null, SDI_FILENAME varchar2(264) not null, SDI_MIME_TYPE varchar2(32) not null, SDI_UPD_DT date, SDI_UPD_UID varchar2(32), SDI_SD_ID number(19,0), primary key (SDI_ID));
create table SIDX_OPTION (SO_ID number(19,0) not null, SO_CR_DT date not null, SO_CR_UID varchar2(32) not null, SO_TEXT varchar2(256) not null, SO_UNIQUE_ID varchar2(48) not null, SO_UPD_DT date, SO_UPD_UID varchar2(32), SO_SQ_ID number(19,0), primary key (SO_ID), unique (SO_TEXT, SO_UNIQUE_ID));
create table SIDX_PAYMENT_HISTORY (SPH_ID number(19,0) not null, SPH_BONUS_AMOUNT number(12,2) not null, SPH_CR_DT date not null, SPH_CR_UID varchar2(32) not null, SPH_CURRENCY varchar2(3) not null, SPH_REMARK varchar2(256), SPH_STATUS varchar2(32) not null, SPH_UPD_DT date, SPH_UPD_UID varchar2(32), SPH_SAP_ID number(19,0), SPH_SPR_ID number(19,0) not null, primary key (SPH_ID));
create table SIDX_POINTS_MATRIX (SPM_ID number(19,0) not null, SPM_CR_DT date not null, SPM_CR_UID varchar2(32) not null, SPM_POINTS number(10,0) not null, SPM_UPD_DT date, SPM_UPD_UID varchar2(32), SPM_OPTION_ID number(19,0) not null, SPM_PARENT_ID number(19,0), SPM_QUESTION_ID number(19,0) not null, primary key (SPM_ID));
create table SIDX_PROFILE_RATE (SPR_ID number(19,0) not null, SPR_BASIC_INDEX number(4,2) not null, SPR_CR_DT date not null, SPR_CR_UID varchar2(32) not null, SPR_MONTH varchar2(2) not null, SPR_STATUS varchar2(32) not null, SPR_UPD_DT date, SPR_UPD_UID varchar2(32), SPR_VALIDATED_INDEX number(4,2) not null, SPR_YEAR varchar2(4) not null, SPR_SCP_ID number(19,0), primary key (SPR_ID));
create table SIDX_QUESTION (SQ_ID number(19,0) not null, SQ_CR_DT date not null, SQ_CR_UID varchar2(32) not null, SQ_MAX_POINTS number(10,0) not null, SQ_TEXT varchar2(256) not null, SQ_UNIQUE_ID varchar2(48) not null, SQ_UPD_DT date, SQ_UPD_UID varchar2(32), SQ_VALIDATION_PROOF number(1,0) not null, SQ_VALIDITY_PERIOD_MNTS number(3,0) not null, SQ_SS_ID number(19,0), primary key (SQ_ID), unique (SQ_TEXT, SQ_UNIQUE_ID));
create table SIDX_QUESTIONNAIRE (SQ_ID number(19,0) not null, SQ_CR_DT date not null, SQ_CR_UID varchar2(32) not null, SQ_REMARK varchar2(256) not null, SQ_STATUS varchar2(48) not null, SQ_UPD_DT date, SQ_UPD_UID varchar2(32), SQ_VERSION varchar2(8) not null, primary key (SQ_ID));
create table SIDX_QUESTIONNAIRE_VERSION (SQV_ID number(19,0) not null, SQV_CR_DT date not null, SQV_CR_UID varchar2(32) not null, SQV_NUMBER number(10,0) not null, SQV_UPD_DT date, SQV_UPD_UID varchar2(32), SQV_QUESTION_ID number(19,0), SQV_QUESTIONNAIRE_ID number(19,0), primary key (SQV_ID));
create table SIDX_QUEUE_DIGITAL_MSTI (SQDM_ID number(19,0) not null, SQDM_CORRELATION_ID varchar2(48) not null, SQDM_CR_DT date not null, SQDM_CR_UID varchar2(32) not null, SQDM_HEADER_TYPE varchar2(48), SQDM_MESSAGE clob not null, SQDM_UPD_DT date, SQDM_UPD_UID varchar2(32), primary key (SQDM_ID));
create table SIDX_QUEUE_MSTI_DIGITAL (SQMD_ID number(19,0) not null, SQMD_CORRELATION_ID varchar2(48) not null, SQMD_CR_DT date not null, SQMD_CR_UID varchar2(32) not null, SQMD_HEADER_TYPE varchar2(48), SQMD_MESSAGE clob not null, SQMD_UPD_DT date, SQMD_UPD_UID varchar2(32), primary key (SQMD_ID));
create table SIDX_REJECTION_REASON (SRR_ID number(19,0) not null, SRR_CR_DT date not null, SRR_CR_UID varchar2(32) not null, SRR_TEXT varchar2(256) not null, SRR_UPD_DT date, SRR_UPD_UID varchar2(32), primary key (SRR_ID));
create table SIDX_SECTION (SS_ID number(19,0) not null, SS_CR_DT date not null, SS_CR_UID varchar2(32) not null, SS_TYPE varchar2(32) not null, SS_UPD_DT date, SS_UPD_UID varchar2(32), primary key (SS_ID));
create index sap_status_ix on SIDX_ANNUAL_PAYMENT (SAP_STATUS);
alter table SIDX_ANNUAL_PAYMENT add constraint FK6C40D2FB8E60C8E3 foreign key (SAP_SCP_ID) references SIDX_CLIENT_PROFILE;
create index sidx_answer_o_id_ix on SIDX_ANSWER (SA_SO_ID);
create index sidx_answer_q_id_ix on SIDX_ANSWER (SA_SQ_ID);
alter table SIDX_ANSWER add constraint FK1B795033D4A697A3 foreign key (SA_SQ_ID) references SIDX_QUESTION;
alter table SIDX_ANSWER add constraint FK1B795033F6DD67F4 foreign key (SA_SO_ID) references SIDX_OPTION;
alter table SIDX_ANSWER add constraint FK1B79503322C3F948 foreign key (SA_CP_ID) references SIDX_CLIENT_PROFILE;
create index sad_expiry_date_ix on SIDX_ANSWER_DOCUMENT (SAD_EXPIRY_DATE);
create index sad_status_ix on SIDX_ANSWER_DOCUMENT (SAD_STATUS);
create index sad_col1_ix on SIDX_ANSWER_DOCUMENT (SAD_ID, SAD_SDI_ID, SAD_SA_ID);
alter table SIDX_ANSWER_DOCUMENT add constraint FKC20A2BA746E7308B foreign key (SAD_SRR_ID) references SIDX_REJECTION_REASON;
alter table SIDX_ANSWER_DOCUMENT add constraint FKC20A2BA7E8E1A2D foreign key (SAD_SDI_ID) references SIDX_DOCUMENT_INSTANCE;
alter table SIDX_ANSWER_DOCUMENT add constraint FKC20A2BA7CA722993 foreign key (SAD_SA_ID) references SIDX_ANSWER;
create index sidx_cn_number_ix on SIDX_CLIENT_NUMBER (SCN_NUMBER);
create index sidx_cn_business_ix on SIDX_CLIENT_NUMBER (SCN_BUSINESS_UNIT);
alter table SIDX_CLIENT_NUMBER add constraint FKCB5880289DEC62D0 foreign key (SCN_CLIENT_PROFILE_ID) references SIDX_CLIENT_PROFILE;
create index sidx_cq_status on SIDX_CLIENT_QUESTIONNAIRE (SCQ_STATUS);
alter table SIDX_CLIENT_QUESTIONNAIRE add constraint FK56328BC46EB8813 foreign key (SCQ_CLIENT_PROFILE_ID) references SIDX_CLIENT_PROFILE;
alter table SIDX_CLIENT_QUESTIONNAIRE add constraint FK56328BC4479ADC26 foreign key (SCQ_QUESTIONNAIRE_ID) references SIDX_QUESTIONNAIRE;
create index sidx_document_type_ix on SIDX_DOCUMENT (SD_TYPE);
create index sdi_sd_ix on SIDX_DOCUMENT_INSTANCE (SDI_SD_ID);
create index sdi_file_uuid_ix on SIDX_DOCUMENT_INSTANCE (SDI_FILE_UUID);
create index sdi_mime_type_ix on SIDX_DOCUMENT_INSTANCE (SDI_MIME_TYPE);
create index sdi_filename_ix on SIDX_DOCUMENT_INSTANCE (SDI_FILENAME);
alter table SIDX_DOCUMENT_INSTANCE add constraint FK9811FBA479395A6F foreign key (SDI_SD_ID) references SIDX_DOCUMENT;
create index sidx_option_q_id_ix on SIDX_OPTION (SO_SQ_ID);
create index sidx_option_u_id_ix on SIDX_OPTION (SO_UNIQUE_ID);
alter table SIDX_OPTION add constraint FK337998EAB93DD531 foreign key (SO_SQ_ID) references SIDX_QUESTION;
create index sph_status_ix on SIDX_PAYMENT_HISTORY (SPH_STATUS);
alter table SIDX_PAYMENT_HISTORY add constraint FK3445A6464E4D4B90 foreign key (SPH_SPR_ID) references SIDX_PROFILE_RATE;
alter table SIDX_PAYMENT_HISTORY add constraint FK3445A6467BB41FC1 foreign key (SPH_SAP_ID) references SIDX_ANNUAL_PAYMENT;
create index spm_option_id_ix on SIDX_POINTS_MATRIX (SPM_OPTION_ID);
create index spm_col2_ix on SIDX_POINTS_MATRIX (SPM_QUESTION_ID, SPM_OPTION_ID, SPM_PARENT_ID);
create index spm_col1_ix on SIDX_POINTS_MATRIX (SPM_QUESTION_ID, SPM_OPTION_ID);
create index spm_question_id_ix on SIDX_POINTS_MATRIX (SPM_QUESTION_ID);
create index spm_parent_id_ix on SIDX_POINTS_MATRIX (SPM_PARENT_ID);
alter table SIDX_POINTS_MATRIX add constraint FKA497E8885C5BFCD7 foreign key (SPM_PARENT_ID) references SIDX_POINTS_MATRIX;
alter table SIDX_POINTS_MATRIX add constraint FKA497E8884766323D foreign key (SPM_QUESTION_ID) references SIDX_QUESTION;
alter table SIDX_POINTS_MATRIX add constraint FKA497E8889609689D foreign key (SPM_OPTION_ID) references SIDX_OPTION;
create index sidx_profile_rate_col1_ix on SIDX_PROFILE_RATE (SPR_ID, SPR_SCP_ID);
create index sidx_profile_rate_scp_id_ix on SIDX_PROFILE_RATE (SPR_SCP_ID);
alter table SIDX_PROFILE_RATE add constraint FK53A4268BE25A3B0 foreign key (SPR_SCP_ID) references SIDX_CLIENT_PROFILE;
create index sidx_validation_proof_ix on SIDX_QUESTION (SQ_VALIDATION_PROOF);
create index sidx_question_uid_ix on SIDX_QUESTION (SQ_UNIQUE_ID);
create index sidx_question_txt_ix on SIDX_QUESTION (SQ_TEXT);
alter table SIDX_QUESTION add constraint FKFAAEFF1BEE20688C foreign key (SQ_SS_ID) references SIDX_SECTION;
create index sidx_questionnaire_version_ix on SIDX_QUESTIONNAIRE (SQ_VERSION);
create index sidx_questionnaire_status_ix on SIDX_QUESTIONNAIRE (SQ_STATUS);
create index sidx_q_version_qid1_ix on SIDX_QUESTIONNAIRE_VERSION (SQV_QUESTIONNAIRE_ID);
create index sidx_q_version_col1_ix on SIDX_QUESTIONNAIRE_VERSION (SQV_ID, SQV_QUESTIONNAIRE_ID, SQV_QUESTION_ID);
create index sidx_q_version_qid2_ix on SIDX_QUESTIONNAIRE_VERSION (SQV_QUESTION_ID);
alter table SIDX_QUESTIONNAIRE_VERSION add constraint FK25D20CE78BDF3665 foreign key (SQV_QUESTION_ID) references SIDX_QUESTION;
alter table SIDX_QUESTIONNAIRE_VERSION add constraint FK25D20CE75DC59F4F foreign key (SQV_QUESTIONNAIRE_ID) references SIDX_QUESTIONNAIRE;
create index sidx_q_d_m_corr_id_ix on SIDX_QUEUE_DIGITAL_MSTI (SQDM_CORRELATION_ID);
create index sidx_q_m_d_corr_id_ix on SIDX_QUEUE_MSTI_DIGITAL (SQMD_CORRELATION_ID);
create sequence SIDX_ANNUAL_PAYMENT_SEQ;
create sequence SIDX_ANSWER_DOCUMENT_SEQ;
create sequence SIDX_ANSWER_SEQ;
create sequence SIDX_CLIENT_NUMBER_SEQ;
create sequence SIDX_CLIENT_PROFILE_SEQ;
create sequence SIDX_CLIENT_QUESTIONNAIRE_SEQ;
create sequence SIDX_DOCUMENT_INSTANCE_SEQ;
create sequence SIDX_DOCUMENT_SEQ;
create sequence SIDX_OPTION_SEQ;
create sequence SIDX_PAYMENT_HISTORY_SEQ;
create sequence SIDX_POINTS_MATRIX_SEQ;
create sequence SIDX_PROFILE_RATE_SEQ;
create sequence SIDX_QUESTIONNAIRE_SEQ;
create sequence SIDX_QUESTIONNAIRE_VERSION_SEQ;
create sequence SIDX_QUESTION_SEQ;
create sequence SIDX_QUEUE_DIGITAL_MSTI_SEQ;
create sequence SIDX_QUEUE_MSTI_DIGITAL_SEQ;
create sequence SIDX_REJECTION_REASON_SEQ;
create sequence SIDX_SECTION_SEQ;