package org.onproject.onstudy.ui.swing.common;

/*
 * Copyright (c) 2005-2006 tomtom@kuroneko
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * �C���[�W��w�i�ɕ`�悵���p�l�����쐬����B<br>
 * �C���[�W�𒣂�t����JLabel�Ƃ̈Ⴂ�̓p�l���̃T�C�Y�̕ύX�ɂƂ��Ȃ�
 * �p�l���S�̂𖄂߂�悤�ɃC���[�W�̃T�C�Y���ύX�����B<br>
 * �C���[�W�̃T�C�Y�ύX�̓p�l���̃T�C�Y�Ɋg��A�k�������^�C�v�ƁA
 * �e�N�X�`�����g���ĕ��ׂăp�l���𖄂߂�悤�ɕ`�悷��^�C�v������B<br>
 *
 * ���񉻂̍ۂɂ̓t�B�[���himage��texture��transient��
 * �Ȃ��Ă��邪�C���[�W�����[�h����Ă����(�Ⴆ�΂P�x�ł��p�l����
 * �\������Ă����)���񉻂����B<br>
 *
 * XMLEncoder�ł͂��܂��i�����ł��܂���B<br>
 *
 * �C���[�W��e�N�X�`����ݒ肷�郁�\�b�h�ň�����Image��ImageIcon��
 * �s���ȏꍇ��image�v���p�e�B��texture�v���p�e�B��null�ɐݒ肳��
 * �w�i�͕\������Ȃ��B<br>
 * �s����Image�I�u�W�F�N�g��ImageIcon�I�u�W�F�N�g�Ƃ�
 * <pre>
 * Image img = Toolkit.getDefaultToolkit().createImage("not_existent_file");
 * ImageIcon imageIcon = new ImageIcon("*:@/---hoge.png");
 * </pre>
 * ��̃R�[�h�̂悤�ɑ��݂��Ȃ��t�@�C����C���[�W�t�@�C���ȊO�A���邢��
 * �T�|�[�g����Ă��Ȃ��C���[�W�t�@�C���Ȃǂ��������č쐬���ꂽ
 * Image�I�u�W�F�N�g��ImageIcon�I�u�W�F�N�g���w���B<br>
 * ������Image�I�u�W�F�N�g��ImageIcon�I�u�W�F�N�g�͐�������
 * ��O�Ȃǂ͔��������A�C���[�W�̃��[�h���ɂ���O�͔������Ȃ��B<br>
 * ���̃N���X�ł������̐������C���[�W��ێ����Ă��Ȃ�
 * Image�I�u�W�F�N�g��ImageIcon�I�u�W�F�N�g�������ꍇ�ɗ�O�Ȃǂ�
 * �X���[���܂���B�i�P�ɔw�i��\�����Ȃ������ł��j
 */
public class ImagePanel extends JPanel {

    /**
     * �p�l���̃T�C�Y�ɂ��킹�Ċg��k�����Ĕw�i�Ƃ��Ďg�p����C���[�W
     */
    transient protected Image image;

    /**
     * �e�N�X�`������ׂăp�l���𕢂��Ĕw�i�Ƃ���ꍇ�Ɏg�p����
     * TexturePaint�I�u�W�F�N�g
     */
    transient protected TexturePaint texture;

    /**
     * �Ō�ɕ`�悵���ۂ̃p�l���T�C�Y��ێ�����Rectangle�I�u�W�F�N�g
     */
    protected Rectangle rect = new Rectangle();

    /**
     * �O��`��Ɏg�p�����w�i�C���[�W�̃L���b�V�� <br>
     */
    transient protected Image cachedImage;

    /**
     * �f�t�H���g�R���X�g���N�^<br>
     * �C���[�W��e�N�X�`����ݒ肵�Ȃ��ꍇ�͂����̓�����JPanel�ƂȂ�B
     */
    public ImagePanel() {
        setOpaque(false);
    }

    /**
     * ������TexturePaint�I�u�W�F�N�g�����e�N�X�`������ׂ�
     * �w�i�Ƃ���ImagePanel�C���X�^���X�����B<br>
     * �C���[�W�t�@�C���Ȃǂ���ł͂Ȃ��A�����ō쐬�����e�N�X�`����
     * �g�p����ꍇ�Ȃǂɂ��̃R���X�g���N�^���g�p����B<br>
     *
     * @param texture �e�N�X�`���Ŏg�p������`�̃C���[�W������
     *                TexturePaint�I�u�W�F�N�g
     *
     * @exception IllegalArgumentException  ������null�̏ꍇ
     */
    public ImagePanel(TexturePaint texture) {
        if (texture == null) {
            throw new IllegalArgumentException("argument is null !");
        }
        this.texture = texture;
        setOpaque(false);
    }

    /**
     * �����̃C���[�W��w�i�ɂ���ImagePanel�C���X�^���X���쐬����B<br>
     *
     * ������isTexture��true�̏ꍇ�͈����̃C���[�W����
     * �e�N�X�`���𐶐����A������p�l���𖄂߂�悤�ɕ��ׂĕ`�悷��B<br>
     * ���̏ꍇ�ɂ�getTexture���\�b�h�͈����̃C���[�W�����
     * ��������ݒ肳��Ă���TexturePaint�I�u�W�F�N�g��Ԃ��B
     * �i������Image�I�u�W�F�N�g��image�v���p�e�B�Ƃ��Đݒ肳�ꂽ���
     * ���Ȃ��̂�getImage���\�b�h��null��Ԃ��j<br>
     *
     * ������isTexture��false�̏ꍇ�͈����̃C���[�W���g��k��������
     * �p�l���S�̂𕢂��悤�ɕ\��������B<br>
     * ���̏ꍇ�ɂ�getImage���\�b�h�͈�����Image�I�u�W�F�N�g��Ԃ��悤��
     * �Ȃ�B<br>
     *
     * @param img �C���[�W���i�[����Image�I�u�W�F�N�g
     * @param isTexture �����̃C���[�W���e�N�X�`���Ƃ��ăp�l���S�̂�
     *                   ���ׂĔw�i�Ƃ���ꍇ�ɂ�true���w�肵�A
     *                   �����̃C���[�W���g��k�������ăp�l���S�̂�
     *                   �\������ꍇ�ɂ�false���w�肷��B
     * @exception IllegalArgumentException  �����̃C���[�W��null�̏ꍇ
     */
    public ImagePanel(Image img, boolean isTexture) {
        setBG(img, isTexture);
        setOpaque(false);
    }

    /**
     * �����̃C���[�W�t�@�C���̃p�X(�J�����g�f�B���N�g���Ȃ�t�@�C����)����
     * �C���[�W���擾���Ă�������Ƃɍ쐬�����w�i������
     * ImagePanel�C���X�^���X�����<br>
     *
     * ������isTexture��true�̏ꍇ�̓p�X����擾�����C���[�W����
     * �e�N�X�`���𐶐����A������p�l���𖄂߂�悤�ɕ��ׂĕ`�悷��B<br>
     * ���̏ꍇ�ɂ�getTexture���\�b�h�̓C���[�W����ɍ쐬����
     * �ݒ肳��Ă���TexturePaint�I�u�W�F�N�g��Ԃ��B<br>
     *
     * ������isTexture��false�̏ꍇ�̓p�X����擾�����C���[�W��
     * �g��k�������ăp�l���S�̂𕢂��悤�ɕ\��������B<br>
     * ���̏ꍇ�ɂ�getImage���\�b�h�̓p�X���琶������Image�I�u�W�F�N�g��
     * �Ԃ��悤�ɂȂ�B<br>
     *
     *
     * @param filePath  �C���[�W�t�@�C���ւ̃p�X
     * @param isTexture �����̃C���[�W���e�N�X�`���Ƃ��ăp�l���S�̂�
     *                  ���ׂĔw�i�Ƃ���ꍇ�ɂ�true���w�肵�A
     *                  �����̃C���[�W���g��k�������ăp�l���S�̂�
     *                  �\������ꍇ�ɂ�false���w�肷��B
     * @exception IllegalArgumentException filePath��null���̕�����̏ꍇ
     */
    public ImagePanel(String filePath, boolean isTexture) {
        if ((filePath == null) || (filePath.length() == 0)) {
            throw new IllegalArgumentException(
                                "filePath is null or empty !");
        }

        // ���݂��Ȃ��t�@�C���̏ꍇ�ł������ŗ�O�͔��������A
        // imageIcon��null�ɂ͂Ȃ�Ȃ��B
        ImageIcon imageIcon = new ImageIcon(filePath);
        setBG(imageIcon, isTexture);
        setOpaque(false);
    }

    /**
     * ������ImageIcon�I�u�W�F�N�g�̂��C���[�W��w�i�ɂ���
     * ImagePanel�C���X�^���X���쐬����B<br>
     *
     * ������isTexture��true�̏ꍇ�͈�����ImageIcon�I�u�W�F�N�g�̕ێ�����
     * �C���[�W����e�N�X�`���𐶐����A������p�l���𖄂߂�悤��
     * ���ׂĕ`�悷��B<br>
     * ���̏ꍇ�ɂ�getTexture���\�b�h�͈�������擾�����C���[�W�����
     * �������ꂽTexturePaint�I�u�W�F�N�g��Ԃ��B
     * �igetImage���\�b�h��null��Ԃ��j<br>
     *
     * ������isTexture��false�̏ꍇ�͈�����ImageIcon�I�u�W�F�N�g�̕ێ�����
     * �C���[�W���g��k�������ăp�l���S�̂𕢂��悤�ɕ\��������B<br>
     * ���̏ꍇ�ɂ�getImage���\�b�h�͈�����Image�I�u�W�F�N�g��Ԃ��悤��
     * �Ȃ�B<br>
     *
     * @param imageIcon �C���[�W���i�[����ImageIcon�I�u�W�F�N�g
     * @param drawType  DrawType.RESIZE(�g��A�k��)��������
     *                  DrawType.PATTERN(�e�N�X�`��)
     *
     * @exception  IllegalArgumentException  ������ImageIcon�I�u�W�F�N�g��
     *                                       null�̏ꍇ
     */
    public ImagePanel(ImageIcon imageIcon, boolean isTexture) {
        setBG(imageIcon, isTexture);
        setOpaque(false);
    }

    /**
     * �p�l���̔w�i�C���[�W��ݒ肷��B<br>
     * �����̃C���[�W�̓p�l���̃T�C�Y�Ɋg��A�k�����ĕ\�������B<br>
     * �e�N�X�`����ݒ肵�Ă���ꍇ�ɂ́A���̃��\�b�h���Ăяo�����ۂ�
     * �j�������̂ŁA���̎Q�Ƃ��K�v�ȏꍇ�ɂ͂��̃��\�b�h�̑O��
     * getTexture���\�b�h�Ŏ擾���Ă����B<br>
     * ���̃��\�b�h��repaint���\�b�h�ɂ��p�l���S�̂̍ĕ`����s���B<br>
     * ������Image�I�u�W�F�N�g��null�̏ꍇ�ɂ�IllegalArgumentException��
     * �X���[����B<br>
     *
     * @param img �p�l���T�C�Y�Ɋg��A�k�����Ĕw�i�Ƃ���Image�I�u�W�F�N�g
     * @exception  IllegalArgumentException  ������null�̏ꍇ
     */
    public void setImage(Image img) {
        if (img == null) {
            throw new IllegalArgumentException("argument is null !");
        }
        if (this.image == img) {
            return;
        }
        setBG(img, false);
        texture = null;
        cachedImage = null;
        repaint();
    }

    /**
     * ���ݐݒ肳��Ă���p�l���T�C�Y�Ɋg��A�k�����Ĕw�i�Ƃ���
     * Image�I�u�W�F�N�g��Ԃ��B<br>
     * �ݒ肳��Ă��Ȃ����null��Ԃ��B
     *
     * @return �g��A�k���ɂ���Ĕw�i�ƂȂ�Image�I�u�W�F�N�g
     */
    public Image getImage() {
        return image;
    }

    /**
     * �����̃e�N�X�`�����p�l���ɕ��ׂĔw�i�Ƃ���B<br>
     * �p�l���T�C�Y�Ɋg��k�����Ĕw�i�Ƃ���Image�I�u�W�F�N�g��
     * �ݒ肳��Ă���ꍇ�ɂ́A���̃��\�b�h���Ăяo�����ۂ�
     * �j�������̂ŁA���̎Q�Ƃ��K�v�ȏꍇ�ɂ�getImage���\�b�h��
     * �擾���Ă��炱�̃��\�b�h���Ăяo���B<br>
     * ���̃��\�b�h��repaint���\�b�h�ɂ��p�l���S�̂̍ĕ`����s���B<br>
     * ������TexturePaint�I�u�W�F�N�g��null�̏ꍇ�ɂ�
     * IllegalArgumentException���X���[����B<br>
     *
     * @param texture �p�l���ɕ��ׂĔw�i�Ƃ���TexturePaint�I�u�W�F�N�g
     * @exception  IllegalArgumentException  ������null�̏ꍇ
     */
    public void setTexture(TexturePaint texture) {
        if (texture == null) {
            throw new IllegalArgumentException("argument is null !");
        }
        if (this.texture == texture) {
            return;
        }
        this.texture = texture;
        image = null;
        cachedImage = null;
        repaint();
    }

    /**
     * ������ImgeIcon�����ƂɃe�N�X�`�����쐬���Ĕw�i�Ƃ���B<br>
     * �p�l���T�C�Y�Ɋg��k�����Ĕw�i�Ƃ���Image�I�u�W�F�N�g��
     * �ݒ肳��Ă���ꍇ�ɂ́A���̃��\�b�h���Ăяo�����ۂ�
     * �j�������̂ŁA���̎Q�Ƃ��K�v�ȏꍇ�ɂ�getImage���\�b�h��
     * �擾���Ă��炱�̃��\�b�h���Ăяo���B<br>
     * ���̃��\�b�h��repaint���\�b�h�ɂ��p�l���S�̂̍ĕ`����s���B<br>
     * ������ImageIcon�I�u�W�F�N�g��null�̏ꍇ�ɂ�
     * IllegalArgumentException���X���[����B<br>
     *
     * @param imageIcon �e�N�X�`���ƂȂ�C���[�W���i�[����
     *                  ImageIcon�I�u�W�F�N�g
     * @exception  IllegalArgumentException  ������null�̏ꍇ
     */
    public void setTextureFromImageIcon(ImageIcon imageIcon) {
        setBG(imageIcon, true);
        image = null;
        cachedImage = null;
        repaint();
    }

    /**
     * ������ImgeIcon�����ƂɃe�N�X�`�����쐬���Ĕw�i�Ƃ���B<br>
     * �p�l���T�C�Y�Ɋg��k�����Ĕw�i�Ƃ���Image�I�u�W�F�N�g��
     * �ݒ肳��Ă���ꍇ�ɂ́A���̃��\�b�h���Ăяo�����ۂ�
     * �j�������̂ŁA���̎Q�Ƃ��K�v�ȏꍇ�ɂ�getImage���\�b�h��
     * �擾���Ă��炱�̃��\�b�h���Ăяo���B<br>
     * ���̃��\�b�h��repaint���\�b�h�ɂ��p�l���S�̂̍ĕ`����s���B<br>
     * ������ImageIcon�I�u�W�F�N�g��null�̏ꍇ�ɂ�
     * IllegalArgumentException���X���[����B<br>
     *
     * @param imageIcon �e�N�X�`���ƂȂ�C���[�W���i�[����
     *                  ImageIcon�I�u�W�F�N�g
     * @exception  IllegalArgumentException  ������null�̏ꍇ
     */
    public void setTextureFromImage(Image img) {
        if (img == null) {
            throw new IllegalArgumentException("argument is null !");
        }
        setBG(img, true);
        image = null;
        cachedImage = null;
        repaint();
    }

    /**
     * �ݒ肳��Ă���e�N�X�`����Ԃ��B<br>
     * �ݒ肳��Ă��Ȃ����null��Ԃ��B<br>
     *
     * @return ���ݐݒ肳��Ă���TexturePaint�I�u�W�F�N�g��Ԃ��B
     *         �ݒ肳��Ă��Ȃ����null��Ԃ��B
     */
    public TexturePaint getTexture() {
        return texture;
    }

    /**
     * �ݒ肳��Ă���C���[�W��e�N�X�`��������Δj������B<br>
     * ���̃��\�b�h���Ăяo�����getImage���\�b�h��getTexture���\�b�h��
     * ����null��Ԃ��悤�ɂȂ�B<br>
     * ���̃p�l���̏�����Ԃ͓����Ȃ̂�setOpaque(true)���Ăяo������
     * �ǂ����ɂ��A�w�i�̖��������ȃp�l���A���邢�͔w�i�̖����s������
     * �p�l���ƂȂ�B<br>
     * ���̃��\�b�h��repaint���\�b�h�ɂ��p�l���S�̂̍ĕ`����s���B
     */
    public void removeBackgroundImage() {
        image = null;
        texture = null;
        cachedImage = null;
        repaint();
    }

    /**
     * �I�[�o�[���C�h�B<br>
     * ���̃N���X�ł̓C���[�W�ƃe�N�X�`���̗������ݒ肳��Ă����Ԃ�
     * ���Ȃ��悤�Ɏ�������Ă��邪�A�T�u�N���X�ł����j��ꍇ�ɂ�
     * ����paintComponent���\�b�h���I�[�o�[���C�h����K�v������B
     *
     * @param g �O���t�B�N�R���e�L�X�g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tempW = rect.width;
        int tempH = rect.height;
        rect.width = getWidth();
        rect.height = getHeight();

        // �L���b�V���C���[�W���L���Ȃ�΂�����g���B
        // ���Ȃ��Ƃ�Sun��Java�����ł́A����C���[�W������X�P�[����
        // �\�������ꍇ�ɂ͎��������C���[�W�A�����X�P�[���Ȃ��
        // �L���b�V�����ꂽ�C���[�W������A���ꂪ�\�������Ƃ̎��B
        // ���̃N���X�ł̓e�N�X�`�����C���[�W�̃T�C�Y�ɕ~���l�߂�
        // �������y������鎖���Ӑ}���ăL���b�V���C���[�W���쐬���Ă��邪�A
        // ����������Ƃ�����Ӗ��̖��������ŋt�Ƀ������̖��ʌ����ƂȂ��Ă���
        // �\��������
        if ((cachedImage != null) && (tempW == rect.width) &&
                                     (tempH == rect.height)) {
            g.drawImage(cachedImage, 0, 0, this);
            return;
        }

        //////// �ȉ��L���b�V���C���[�W�������ȏꍇ�̏��� ////////

        // �w�i�ƂȂ�C���[�W���쐬���AdrawImage�ŃO���t�B�N�R���e�L�X�g��
        // �`�悷��B�쐬�����C���[�W�̓L���b�V�����Ă����A����̕`�掞�ɂ�
        // �L���Ȃ�Ύg���B
        // �g��k���ŕ`�悷��ꍇ�ɃC���[�W���ݒ肳��Ă��Ȃ��ꍇ��
        // �e�N�X�`���ŕ`�悷��ꍇ��TexturePaint�I�u�W�F�N�g��
        // �ݒ肳��Ă��Ȃ��ꍇ�ɂ͉����`�悵�Ȃ�(�����̓����p�l��)�B
        // �C���[�W�ƃe�N�X�`���̗������ݒ肳��Ă����Ԃ͍��Ȃ��B
        if (image != null) {
            cachedImage = createImage(rect.width, rect.height);
            Graphics cachedG = cachedImage.getGraphics();
            cachedG.drawImage(image,0, 0,
                              rect.width, rect.height, this);
            cachedG.dispose();
            g.drawImage(cachedImage, 0, 0, this);
        } else if (texture != null) {
            cachedImage = createImage(rect.width, rect.height);
            Graphics2D cachedG = (Graphics2D)cachedImage.getGraphics();
            cachedG.setPaint(texture);
            cachedG.fill(rect);
            cachedG.dispose();
            g.drawImage(cachedImage, 0, 0, this);
        }
    }

    /**
     * ������isTexture��true�̏ꍇ�͈�����ImageIcon�I�u�W�F�N�g�̕ێ�����
     * �C���[�W����e�N�X�`���𐶐����A�t�B�[���htexture�ɐݒ肷��B
     * �i���̏ꍇ�Ƀt�B�[���himage��null�ɂ͂��Ȃ��j<br>
     * ������isTexture��false�̏ꍇ�͈�����ImageIcon�I�u�W�F�N�g�̕ێ�����
     * �C���[�W���t�B�[���himage�ɐݒ肷��B�i���̏ꍇ��
     * �t�B�[���htexture��null�ɂ͂��Ȃ��j<br>
     *
     * @param imageIcon �C���[�W���i�[����ImageIcon�I�u�W�F�N�g
     * @param isTexture �����̃C���[�W���e�N�X�`���Ƃ��ăp�l���S�̂�
     *                   ���ׂĔw�i�Ƃ���ꍇ�ɂ�true���w�肵�A
     *                   �����̃C���[�W���g��k�������ăp�l���S�̂�
     *                   �\������ꍇ�ɂ�false���w�肷��B
     *
     * @exception  IllegalArgumentException  ������ImageIcon�I�u�W�F�N�g��
     *                                       null�̏ꍇ
     */
    protected final void setBG(ImageIcon imageIcon, boolean isTexture) {
        if (imageIcon == null) {
            throw new IllegalArgumentException(
                                   "argument (imageIcon) is null !");
        }
        if (isTexture) {
            //imageIcon���������C���[�W�����Ă��Ȃ��ꍇ��null�ɂȂ�
            texture = createTexturePaint(imageIcon);
        } else {
            this.image = imageIcon.getImage();

            //���݂��Ȃ��C���[�W�t�@�C���������ɍ쐬����
            //ImageIcon�I�u�W�F�N�g�̏ꍇ��paintComponent�ŗ]�v�ȏ�����
            //���Ȃ��悤��image��null��ݒ肷��B
            if ((this.image != null) &&
                ((imageIcon.getIconWidth() <= 0) ||
                                   (imageIcon.getIconHeight() <= 0))) {
                this.image = null;
            }
        }
    }

    /**
     * ������isTexture��true�̏ꍇ�͈����̃C���[�W����
     * �e�N�X�`���𐶐����A������t�B�[���htexture�ɐݒ肷��B
     * �i���̏ꍇ�Ƀt�B�[���himage��null�ɂ͂��Ȃ��j<br>
     *
     * ������isTexture��false�̏ꍇ�͈����̃C���[�W��image��
     * �ݒ肷��B�i���̏ꍇ�Ƀt�B�[���htexture��null�ɂ͂��Ȃ��j
     *
     * @param img �C���[�W���i�[����Image�I�u�W�F�N�g
     * @param isTexture �����̃C���[�W���e�N�X�`���Ƃ��ăp�l���S�̂�
     *                   ���ׂĔw�i�Ƃ���ꍇ�ɂ�true���w�肵�A
     *                   �����̃C���[�W���g��k�������ăp�l���S�̂�
     *                   �\������ꍇ�ɂ�false���w�肷��B
     * @exception IllegalArgumentException  �����̃C���[�W��null�̏ꍇ
     */
    protected final void setBG(Image img, boolean isTexture) {
        if (img == null) {
            throw new IllegalArgumentException(
                                   "argument (img) is null !");
        }
        int imageWidth = img.getWidth(this);
        int imageHeight = img.getHeight(this);

        // �C���[�W���܂����S�Ƀ��[�h����Ă��Ȃ��ꍇ
        if ((imageWidth < 0) || (imageHeight < 0)) {
            setBG(new ImageIcon(img), isTexture);
            return;
        }

        ////////// �ȉ�imageWidth, imageHeight�Ƃ���0�ȏ�̏ꍇ //////////
        if (isTexture) {
            if ((imageWidth <= 0) || (imageHeight <= 0)) {
                texture = null;
            } else {
                texture = createTexturePaint(img, imageWidth, imageHeight);
            }
        } else {
            if ((imageWidth <= 0) || (imageHeight <= 0)) {
                this.image = null;
            } else {
                this.image = img;
            }
        }
    }

    /**
     * ImageIcon�I�u�W�F�N�g����TexturePaint�I�u�W�F�N�g�𐶐����ĕԂ��B<br>
     * ������ImageIcon�I�u�W�F�N�g�ɗL���ȃC���[�W���i�[����Ă��Ȃ��ꍇ��
     * null��Ԃ��B
     *
     * @param  imageIcon  �C���[�W���i�[����ImageIcon�I�u�W�F�N�g
     * @return ������ImageIcon����ɍ��ꂽTexturePaint�I�u�W�F�N�g��Ԃ��B
     *         ImageIcon���L���ȃC���[�W��ێ����Ă��Ȃ������ꍇ��
     *         null��Ԃ��B
     */
    protected final TexturePaint createTexturePaint(ImageIcon imageIcon) {
        if (imageIcon == null) {
            throw new IllegalArgumentException(
                                   "argument (imageIcon) is null !");
        }

        //�C���[�W�̎擾�B�`��ł��Ȃ��C���[�W�Ȃ�null��Ԃ��B
        Image img = imageIcon.getImage();
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        if ((img == null) || (imageIcon.getIconWidth() <= 0) ||
                             (imageIcon.getIconHeight() <= 0)) {
            return null;
        }
        return createTexturePaint(img, imageWidth, imageHeight);
    }

    /**
     * �����̃C���[�W��������Ŏw�肵�����A�����̃e�N�X�`����`�悷��
     * TexturePaint�I�u�W�F�N�g���쐬���A�t�B�[���htexture�ɐݒ肷��B<br>
     * ���̃��\�b�h�̈�����Image�̓��[�h�ς݂łȂ���΂Ȃ�Ȃ��B<br>
     *
     * @param img �e�N�X�`���ƂȂ�Image�I�u�W�F�N�g
     * @param imageWidth  �e�N�X�`���̕�
     * @param imageHeight �e�N�X�`���̍���
     * @return �����̕��A�����ɃX�P�[�����O���ꂽ�C���[�W�̃e�N�X�`����
     *         �`�悷��ׂ�TexturePaint�I�u�W�F�N�g
     * @exception IllegalArgumentException �����̃C���[�W��null�̏ꍇ
     * @exception IllegalArgumentException �����̕��A������0�ȉ��̏ꍇ
     */
    protected final TexturePaint createTexturePaint(Image img,
                                                    int imageWidth,
                                                    int imageHeight) {
        if (img == null) {
            throw new IllegalArgumentException("argument (img) is null !");
        }
        if ((imageWidth <= 0) || (imageHeight <= 0)) {
            throw new IllegalArgumentException(
                    "(imageWidth <= 0) || (imageHeight <= 0)");
        }

        // �e�N�X�`���ƂȂ�C���[�W
        BufferedImage textureImg = null;

        if (img instanceof BufferedImage) {
            textureImg = (BufferedImage)img;
        } else {
            textureImg = new BufferedImage(imageWidth, imageHeight,
                                           BufferedImage.TYPE_INT_ARGB);
            Graphics2D textureG = textureImg.createGraphics();

            textureG.drawImage(img, 0, 0, imageWidth, imageHeight, this);
            textureG.dispose();
        }
        Rectangle rect = new Rectangle(0, 0, imageWidth, imageHeight);

        return new TexturePaint(textureImg, rect);
    }




    ////////// ����ȍ~�͒��񉻂ƕ����ɕK�v�ȃ��\�b�h�̎��� //////////

    ///// ���񉻂̍ۂɂ̂ݎg�p����萔 /////
    private static final int RECTANGLE_INT    = 1;
    private static final int RECTANGLE_FLOAT  = 2;
    private static final int RECTANGLE_DOUBLE = 3;

    /*
     * ���񉻂��ꂽ�f�[�^�̕����̍ۂɌĂяo����郁�\�b�h�B
     * �f�t�H���g�̕����̌��writeObject�ŏ����o�������̃f�[�^��
     * �ǂݍ��݃t�B�[���h�̒l�𕜌�����B
     */
    private void readObject(ObjectInputStream in) throws IOException,
                                                  ClassNotFoundException {
        in.defaultReadObject();

        //image�̕���
        int width = in.readInt();
        int height = in.readInt();
        int[] pixels = (int[])in.readObject();

        if (pixels != null) {
            ColorModel colorModel = ColorModel.getRGBdefault();
            MemoryImageSource source = new MemoryImageSource(
                                           width, height, colorModel,
                                           pixels, 0, width);
            image = Toolkit.getDefaultToolkit().createImage(source);
        }

        //texturePaint�̕���
        int tpImageWidth = in.readInt();
        int tpImageHeight = in.readInt();

        int rectType = in.readInt();
        Rectangle2D rectangle;

        if (rectType == RECTANGLE_INT) {
            rectangle = new Rectangle(in.readInt(), in.readInt(),
                                      in.readInt(), in.readInt());
        } else if (rectType == RECTANGLE_FLOAT) {
            rectangle = new Rectangle2D.Float(
                                    in.readFloat(), in.readFloat(),
                                    in.readFloat(), in.readFloat());
        } else {
            rectangle = new Rectangle2D.Double(
                                    in.readDouble(), in.readDouble(),
                                    in.readDouble(), in.readDouble());
        }

        int[] tpPixels = (int[])in.readObject();

        if ((tpPixels != null) && (tpPixels.length > 0)) {
            BufferedImage bufferedImage = new BufferedImage(
                                             tpImageWidth,
                                             tpImageHeight,
                                             BufferedImage.TYPE_INT_ARGB);
            bufferedImage.setRGB(0, 0, tpImageWidth, tpImageHeight,
                                 tpPixels, 0, tpImageWidth);
            texture = new TexturePaint(bufferedImage, rectangle);
        }
    }

    /*
     * ���񉻂̍ۂɌĂяo����郁�\�b�h�B
     * �f�t�H���g�̒��񉻂��s�������transient��image texture�̂Q��
     * �t�B�[���h�̕����ɕK�v�ȏ����������ށB
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        //image�̒���
        int imageWidth    = -1;
        int imageHeight   = -1;
        int[] imagePixels = null;

        if (image != null) {
            imageWidth = image.getWidth(this);
            imageHeight = image.getHeight(this);
            if ((imageWidth > 0) && (imageHeight > 0)) {
                imagePixels = getImagePixels(imageWidth,
                                             imageHeight, image);
            }
        }
        out.writeInt(imageWidth);
        out.writeInt(imageHeight);
        out.writeObject(imagePixels);

        //texture�̒���
        int tpImageWidth = -1;
        int tpImageHeight = -1;
        int rectType = RECTANGLE_INT;
        int[] tpPixels = null;
        Rectangle2D rectangle = null;

        if (texture != null) {
            rectangle = texture.getAnchorRect();

            rectType = (rectangle instanceof Rectangle) ?
                       RECTANGLE_INT :
                       (rectangle instanceof Rectangle2D.Float) ?
                       RECTANGLE_FLOAT : RECTANGLE_DOUBLE;


            BufferedImage tpImage = texture.getImage();
            if (tpImage != null) {
                tpImageWidth = tpImage.getWidth();
                tpImageHeight = tpImage.getHeight();
                if ((tpImageWidth > 0) && (tpImageHeight > 0)) {
                    tpPixels = tpImage.getRGB(0, 0, tpImageWidth,
                                              tpImageHeight, null,
                                              0, tpImageWidth);
                }
            }
        }

        out.writeInt(tpImageWidth);
        out.writeInt(tpImageHeight);
        out.writeInt(rectType);

        if (rectangle == null) {
            rectangle = new Rectangle();
        }

        if (rectType == RECTANGLE_INT) {
            Rectangle rect = (Rectangle)rectangle;
            out.writeInt(rect.x);
            out.writeInt(rect.y);
            out.writeInt(rect.width);
            out.writeInt(rect.height);
        } else if (rectType == RECTANGLE_FLOAT) {
            Rectangle2D.Float rect = (Rectangle2D.Float)rectangle;
            out.writeFloat(rect.x);
            out.writeFloat(rect.y);
            out.writeFloat(rect.width);
            out.writeFloat(rect.height);
        } else {
            Rectangle2D.Double rect = (Rectangle2D.Double)rectangle;
            out.writeDouble(rect.x);
            out.writeDouble(rect.y);
            out.writeDouble(rect.width);
            out.writeDouble(rect.height);
        }
        out.writeObject(tpPixels);
    }

    /**
     * �C���[�W����f�t�H���g�̃J���[���f���Ńs�N�Z���f�[�^�����o���B
     */
    private int[] getImagePixels(int width, int height, Image image) {
        int[] pixels = new int[width * height];
        try {
            PixelGrabber pg = new PixelGrabber(image, 0, 0,
                                               width, height,
                                               pixels, 0, width);
            pg.grabPixels();
            if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                pixels = null;
            }
        }
        catch (InterruptedException e) {
            pixels = null;
        }
        return pixels;
    }
}